Vue.component('file-input', {
  data: function () {
    return {
      xml: null,
      file: null
    }
  },
  props: ['path', 'name'],
  template: '<b-container class="text-left"><b-form-file v-model="file":state="Boolean(file)"placeholder="Select Document..." drop-placeholder="Drop Document here..." accept=".xml" @change="onFileChange"></b-form-file></b-container>',
  methods: {
      onFileChange(e) {
          var vm = this;
          this.xml = e.target.files[0];
          var formData = new FormData();
          formData.append('file', this.xml);
          axios.post(this.path,
                    formData, {
                      headers: {
                        'Content-Type': 'multipart/form-data'
                      }
                    })
                  .then(response => {
                    if (vm.name == 'sourceTree') {
                        app.sourceTreeData = response.data;
                    } else {
                        app.targetTreeData = response.data;
                    }
                    })
                  .catch(function () {
                    console.log('FAILURE!!');
          });

      }
  }

});

Vue.component('compare-button', {
    template: '<button type="button" class="btn btn-success" v-on:click="compareDocuments()" :disabled="!enabledButton">Compare Documents</button>',
    computed: {
        enabledButton() {
            return this.$root.$data.sourceTreeData.name != "Please load the source document" && this.$root.$data.targetTreeData.name != "Please load the target document";
        }
    },
    methods: {
        compareDocuments() {
            axios
                .get('/api/analysis/compare')
                .then(response => {
                    app.results = response.data;
                    for (var i=0; i<app.results.length; i++) {
                        if (app.results[i].resultType == "ATTRIBUTEMISMATCH") {
                            app.attributeresults.push(app.results[i]);
                        } else if (app.results[i].resultType == "VALUEMISMATCH") {
                            app.valueresults.push(app.results[i]);
                        } else if (app.results[i].resultType == "SECTIONMISMATCH") {
                            app.sectionresults.push(app.results[i]);
                        }

                    }
                 })
                .catch(error => console.log(error));
        }
    }

});

Vue.component('result-list', {
  data: function () {
    return {
      resultStyle: false
    }
  },
  props: ['output', 'sourceid', 'targetid', 'type'],
  template: '<li type="button" class="list-group-item list-group-item-action" v-bind:class="{ resultbackground: this.resultStyle}" v-on:click="openTrees()">{{ output }}<b-badge class="badge-default float-right m-2" v-on:click.stop="addDefect()"><i class="fas fa-exclamation"></i></i></b-badge><b-badge class="badge-default float-right m-2" v-on:click.stop="removeResult()"><i class="fas fa-times"></i></b-badge></li>',
  methods: {
    openTrees() {
        //go through source data recursively
        var objectSourceList = [];
        var objectTargetList = [];

        getParentNodes(objectSourceList, app.sourceTreeData, this.sourceid);
        getParentNodes(objectTargetList, app.targetTreeData, this.targetid);

        closeAllNodes(app.sourceTreeData);
        closeAllNodes(app.targetTreeData);

        openNodes(objectSourceList);
        openNodes(objectTargetList);

    },
    removeResult() {
        for (var i=0; i<app.results.length; i++) {
            if (app.results[i].sourceid == this.sourceid && app.results[i].targetid == this.targetid) {
                app.results.splice(i,1);
                break;
            }
        }

        var arr = [];

        if (this.type == "VALUEMISMATCH") {
            arr = app.valueresults;
        } else if (this.type == "SECTIONMISMATCH") {
            arr = app.sectionresults;
        } else if (this.type == "ATTRIBUTEMISMATCH") {
            arr = app.attributeresults;
        }

        for (var j=0;j<arr.length;j++) {
            if (arr[j].sourceid == this.sourceid && arr[j].targetid == this.targetid) {
                arr.splice(j,1);
                break;
            }
        }
    },
    addDefect() {
//        this.removeResult();
        this.resultStyle = !this.resultStyle;
        app.defectresults.push(this);
    }
  }

});

Vue.component('tree-item', {
  template: '#item-template',
  props: {
    item: Object,
    open: Boolean
  },
  computed: {
    isFolder: function () {
      return (this.item.children &&
        this.item.children.length)
    },
    isAttribute: function() {
      return this.item.attribute
    },
    isOpen: function() {
      return this.item.open
    }
  },
  methods: {
    toggle: function () {
      if (this.isFolder) {
        this.item.open = !this.item.open
      }
    }
    }
});

var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue.js!',
//    all: true,
    value: true,
    section: true,
    attribute: true,
    results: [],
    valueresults: [],
    sectionresults: [],
    attributeresults: [],
    defectresults: [],
    sourceTreeData: {name: "Please load the source document", children: [], attribute: 0},
    targetTreeData: {name: "Please load the target document", children: [], attribute: 0}
  },
  computed: {
        getResults: function() {
            var retList = [];
            if (this.value) {
                retList = retList.concat(this.valueresults);
            }
            if (this.section) {
                retList = retList.concat(this.sectionresults);
            }
            if (this.attribute) {
                retList = retList.concat(this.attributeresults);
            }
            return retList;
        },
        getAll: function() {
            return this.attribute && this.value && this.section
        },
        selectAll: {
            get: function(value) {
                return this.value && this.section && this.attribute;
            },
            set: function(value) {
                if (value) {
                    this.value = true;
                    this.section = true;
                    this.attribute = true;
                } else {
                    this.value = false;
                    this.section = false;
                    this.attribute = false;
                }
            }
        }
  }
});

function openNodes(nodeList) {
    for (var i=0; i<nodeList.length; i++) {
        nodeList[i].open = !nodeList[i].open
    }
};

function closeAllNodes(node) {
    node.open = false;
    for (var i=0;node.children && i<node.children.length; i++) {
        closeAllNodes(node.children[i]);
    }
};

function getParentNodes(objectList, node, id) {
    if (node == null) {
        return false;
    }

    objectList.push(node);

    if (node.id == id) {
        return true;
    }

    for (var i=0;node.children && i<node.children.length; i++) {
        var flag = false;
        if (getParentNodes(objectList, node.children[i], id)) {
            flag = true;
            break;
        }
    }

    if (flag) {
        return true;
    }

    objectList.pop();

    return false;
};