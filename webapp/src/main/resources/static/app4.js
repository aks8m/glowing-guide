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
    template: '<button type="button" class="btn btn-success btn-lg btn-block" v-on:click="compareDocuments()" :disabled="!enabledButton">Compare Documents</button>',
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

Vue.component('section-compare-button', {
    template: '<button type="button" class="btn btn-success btn-lg btn-block" v-on:click="compareDocuments()" :disabled="!enabledButton"> Compare Sections </button>',
    computed: {
        enabledButton() {
            return this.$root.$data.sourceSectionData.name != "Please select source section" && this.$root.$data.targetSectionData.name != "Please select target section";
        }
    },
    methods: {
        compareDocuments() {
            axios({
                method: 'post',
                url: '/api/analysis/compareSection',
                data: {
                    source: app.sourceSectionData,
                    target: app.targetSectionData
                }
            })
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
      resultStyle: false,
      defect: false
    }
  },
  props: ['output', 'sourceid', 'targetid', 'type'],
  template: '<li type="button" class="list-group-item list-group-item-action" v-bind:class="{ resultbackground: this.resultStyle}" v-on:click="openTrees()">{{ output }}<b-badge class="badge-default float-right m-2" v-on:click.stop="addDefect()"><i class="fas fa-exclamation"></i></i></b-badge><b-badge class="badge-default float-right m-2" v-on:click.stop="removeResult()"><i class="fas fa-times"></i></b-badge></li>',
  methods: {
    openTrees() {
        //go through source data recursively
        var objectSourceList = [];
        getParentNodes(objectSourceList, app.sourceTreeData, this.sourceid);
        closeAllNodes(app.sourceTreeData);
        openNodes(objectSourceList);

        closeAllNodes(app.targetTreeData);
        for (var i=0; i<this.targetid.length;i++) {
            var objectTargetList = [];
            getParentNodes(objectTargetList, app.targetTreeData, this.targetid[i]);
            openNodes(objectTargetList);
        }



    },
    removeResult() {
        for (var i=0; i<app.results.length; i++) {
            if (app.results[i].sourceid == this.sourceid && app.results[i].targetid == this.targetid) {
                app.deletedresults.push(app.results[i]);
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

        for (var t=0; t<app.defectresults.length; t++) {
            if (app.defectresults[t].sourceid == this.sourceid && app.defectresults[t].targetid == this.targetid) {
                app.defectresults.splice(t,1);
                break;
            }
        }
    },
    addDefect() {
        this.resultStyle = !this.resultStyle;
        if (!this.defect) {
            app.defectresults.push(this);
            this.defect = true;
        } else {
            for (var j=0;j<app.defectresults.length;j++) {
                if (app.defectresults[j].sourceid == this.sourceid && app.defectresults[j].targetid == this.targetid) {
                    app.defectresults.splice(j,1);
                    this.defect = false;
                    break;
                }
            }
        }

    }
  }

});

Vue.component('tree-item', {
  template: '#item-template',
  props: {
    item: Object
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
    },
    isError: function() {
      return this.item.error
    }
  },
  methods: {
    toggle: function () {
      if (this.isFolder) {
        this.item.open = !this.item.open
        this.item.res = !this.item.res
      }
    }
    }
});

Vue.component('tree-item-section-selection', {
    template: '#section-selection',
    props: {
        item: Object,
        source: Boolean
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
        },
        isError: function() {
          return this.item.error
        }
      },
      methods: {
        toggle: function () {
          if (this.isFolder) {
    //        app.sourceSectionData = this.item
            this.item.open = !this.item.open
            this.item.res = !this.item.res
          }
        },
        setSectionData: function(source) {
            if(source) {
                app.sourceSectionData = this.item;
            } else {
                app.targetSectionData = this.item;
            }
        }
      }
});

var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue.js!',
    value: true,
    section: true,
    attribute: true,
    results: [],
    search: "",
    valueresults: [],
    sectionresults: [],
    attributeresults: [],
    defectresults: [],
    deletedresults: [],
    sourceTreeData: {name: "Please load the source document", children: [], attribute: 0},
    targetTreeData: {name: "Please load the target document", children: [], attribute: 0},
    displayInstructions: true,
    displayCompare: false,
    automatic: true,
    sectionModal: true,
    sourceSectionData: {name: "Please select source section", children: [], attribute: 0},
    targetSectionData: {name: "Please select target section", children: [], attribute: 0}
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
        getFilteredResults: function() {
            var list = this.getResults;
            var self = this;
            return list.filter(function(cust){return cust.output.toLowerCase().indexOf(self.search.toLowerCase())>=0;});
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
  },
  methods: {
    addDeleted: function() {
        for (var i=0; i<this.deletedresults.length; i++) {
            this.results.push(this.deletedresults[i]);
            if (this.deletedresults[i].resultType == "VALUEMISMATCH") {
                this.valueresults.push(this.deletedresults[i]);
            } else if (this.deletedresults[i].resultType == "SECTIONMISMATCH") {
                this.sectionresults.push(this.deletedresults[i]);
            } else if (this.deletedresults[i].resultType == "ATTRIBUTEMISMATCH") {
                this.attributeresults.push(this.deletedresults[i]);
            }
        }
        this.deletedresults = []
    },
    clickInstructions: function() {
        this.displayInstructions = true;
        this.displayCompare = false;
    },
    clickCompare: function() {
        this.displayInstructions = false;
        this.displayCompare = true;
    },
    setAutomatic: function() {
        this.automatic = true;
    },
    setManual: function() {
        this.automatic = false;
    },
    closeEveryNode: function() {
        closeAllNodes(this.sourceTreeData);
        closeAllNodes(this.targetTreeData);
    }
  }
});

function openNodes(nodeList) {
    for (var i=0; i<nodeList.length; i++) {
        nodeList[i].open = true;
    }
    nodeList[nodeList.length-1].error=true;
};

function closeAllNodes(node) {
    node.open = false;
    node.error = false;
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