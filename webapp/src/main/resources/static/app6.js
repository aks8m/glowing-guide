Vue.component('file-input', {
  data: function () {
    return {
      xml: null,
      file: null
    }
  },
  computed: {
    colorState: function() {
        if (this.name == 'sourceTree') {
            return this.$root.$data.sourceTreeData.name != "Please load the source document";
        } else {
            return this.$root.$data.targetTreeData.name != "Please load the target document";
        }
    }
  },
  props: ['path', 'name'],
  template: '<b-container class="text-left"><b-form-file v-model="file":state="this.colorState" placeholder="Select Document..." drop-placeholder="Drop Document here..." accept=".xml" @change="onFileChange" style="overflow: hidden;"></b-form-file></b-container>',
  methods: {
      onFileChange(e) {
          var vm = this;
          this.xml = e.target.files[0];
          var formData = new FormData();
          formData.append('file', this.xml);
          if (vm.name == 'sourceTree') {
            app.sourceSpinner = true;
          } else {
            app.targetSpinner = true;
          }
          axios.post(this.path,
                    formData, {
                      headers: {
                        'Content-Type': 'multipart/form-data'
                      }
                    })
                  .then(response => {
                    if (vm.name == 'sourceTree') {
                        app.sourceTreeData = response.data;
                        app.sourceSectionID = response.data.id;
                        app.sourceSpinner = false;
                    } else {
                        app.targetTreeData = response.data;
                        app.targetSectionID = response.data.id;
                        app.targetSpinner = false;
                    }
                    })
                  .catch(function () {
                    console.log('FAILURE!!');
          });

      }
  }

});

Vue.component('section-compare-button', {
    template: '<button type="button" class="btn btn-success btn-block" v-on:click="compareDocuments(); clickInstructions()" :disabled="!enabledButton"> Compare</button>',
    computed: {
        enabledButton: function() {
            return this.$root.$data.sourceTreeData.name != "Please load the source document" && this.$root.$data.targetTreeData.name != "Please load the target document";
        }
    },
    methods: {
        compareDocuments() {
            this.reset();
            closeAllNodes(app.sourceTreeData);
            closeAllNodes(app.targetTreeData);

            var objectSourceList = [];
            getParentNodes(objectSourceList, app.sourceTreeData, app.sourceSectionID);
            closeAllNodes(app.sourceTreeData);
            openNodes(objectSourceList);
            objectSourceList[objectSourceList.length-1].open=false;
            objectSourceList[objectSourceList.length-1].error=false;


            var objectTargetList = [];
            getParentNodes(objectTargetList, app.targetTreeData, app.targetSectionID);
            closeAllNodes(app.targetTreeData);
            openNodes(objectTargetList);
            objectTargetList[objectTargetList.length-1].open=false;
            objectTargetList[objectTargetList.length-1].error=false;


            app.compareSpinner = true;
            axios.get("/api/analysis/compare/" + app.sourceSectionID + "/" + app.targetSectionID)
            .then(response => {
                app.results = response.data;
                for (var i=0; i<app.results.length; i++) {
                    if (app.results[i].resultType == "ATTRIBUTEMISMATCH") {
                        app.attributeresults.push(app.results[i]);
                    } else if (app.results[i].resultType == "VALUEMISMATCH") {
                        app.valueresults.push(app.results[i]);
                    } else if (app.results[i].resultType == "SECTIONMATCHNOTFOUND") {
                        app.sectionresults.push(app.results[i]);
                    }

                }
                app.compareSpinner = false;
             })
            .catch(error => console.log(error));
        },
        clickInstructions() {
            app.displayCompare = true;
            app.displayInstructions = false;
        },
        reset() {
            app.results = []
            app.valueresults = []
            app.sectionresults = []
            app.attributeresults = []
        }
    }

});

Vue.component('result-list', {
  data: function () {
    return {
//      resultStyle: false,
//      removeStyle: false,
////      defect: false
    }
  },
  props: ['output', 'sourceid', 'targetid', 'type'],
  template: '<li type="button" class="list-group-item list-group-item-action" v-bind:class="{ resultbackground: this.defect, removebackground: this.removed}" v-on:click="openTrees()">{{ output }}<b-badge class="badge-default float-right m-2" v-on:click.stop="addDefect()"><i class="fas fa-exclamation"></i></b-badge><b-badge class="badge-default float-right m-2" v-on:click.stop="removeResult()"><i class="fas fa-times"></i></b-badge></li>',
  computed: {
    defect() {
        for (var i=0; i<app.results.length; i++) {
            if (this.sourceid == app.results[i].sourceid && this.targetid == app.results[i].targetid) {
                if (app.results[i].defect) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    },
    removed() {
        for (var i=0; i<app.results.length; i++) {
            if (this.sourceid == app.results[i].sourceid && this.targetid == app.results[i].targetid) {
                if (app.results[i].removed) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
  },
  methods: {
    openTrees() {
        axios.get("/api/analysis/openSourceResult/" + this.sourceid)
                      .then(response => {
                            app.sourceTreeData = response.data;
                      })
                      .catch(function () {
                        console.log('FAILURE!!');
              });
        axios.get("/api/analysis/openTargetResult/" + this.targetid)
                      .then(response => {
                            app.targetTreeData = response.data;
                      })
                      .catch(function () {
                        console.log('FAILURE!!');
              });
    },
    removeResult() {
        for (var i=0; i<app.results.length; i++) {
            if (this.sourceid == app.results[i].sourceid && this.targetid == app.results[i].targetid) {
                app.results[i].removed = !app.results[i].removed;
                app.results[i].defect = false;
//                this.removeStyle = ! this.removeStyle;
//                this.resultType = false;
                break;
            }

        }
    },
    addDefect() {
        for (var i=0; i<app.results.length; i++) {
            if (this.sourceid == app.results[i].sourceid && this.targetid == app.results[i].targetid) {
                app.results[i].defect = !app.results[i].defect;
                app.results[i].removed = false;
//                this.resultStyle = !this.resultType;
//                this.removeStyle = false;
                break;
            }

        }
    }
  }

});

Vue.component('tree-item', {
  template: '#item-template',
  props: {
    item: Object,
    name: String,
    depth: Number
  },
  computed: {
    isFolder: function () {
      return this.item.folder
    },
    isAttribute: function() {
      return (this.item.attribute && this.item.attribute.length)
    },
    isOpen: function() {
      return this.item.open
    },
    isError: function() {
      return this.item.error
    },
    checked: function() {
//        get: function(value) {
            if (this.name == "sourceTree") {
                return app.sourceSectionID == this.item.id
            } else {
                return app.targetSectionID == this.item.id
            }
//        },
//        set: function(value) {
            if (this.name == "sourceTree"){
                app.sourceSectionID = this.item.id
            } else {
                app.targetSectionID = this.item.id
            }
//        }
    },
    indent() {
        return "padding-left: " + ((this.depth * 20) + 15) + "px;"
    }
  },
  methods: {
    toggle: function () {

        var idvar = this.item.id;
        var path;
        if (this.name == "sourceTree") {
            if (this.isOpen) {
                path = '/api/analysis/closeSourceNode/' + idvar;
            } else {
                path = '/api/analysis/openSourceNode/' + idvar;
            }

            axios.get(path)
                      .then(response => {
                            app.sourceTreeData = response.data;
                      })
                      .catch(function () {
                        console.log('FAILURE!!');
              });

        } else {
            if (this.isOpen) {
                path = '/api/analysis/closeTargetNode/' + idvar;
            } else {
                path = '/api/analysis/openTargetNode/' + idvar;
            }

            axios.get(path)
                      .then(response => {
                            app.targetTreeData = response.data;
                      })
                      .catch(function () {
                        console.log('FAILURE!!');
              });
        }




    },
    clickCheck: function(value) {
        if (this.name == "sourceTree"){
            app.sourceSectionID = this.item.id
        } else {
            app.targetSectionID = this.item.id
        }
    }
    }
});

var app = new Vue({
  el: '#app',
  data: {
    value: true,
    section: true,
    attribute: true,
    sourceSpinner: false,
    targetSpinner: false,
    compareSpinner: false,
    results: [],
    search: "",
//    valueresults: [], //
//    sectionresults: [], //
//    attributeresults: [], //
//    defectresults: [], //
//    deletedresults: [], //
    sourceTreeData: {name: "Please load the source document", children: [], attribute: 0},
    targetTreeData: {name: "Please load the target document", children: [], attribute: 0},
    displayInstructions: true,
    displayCompare: false,
//    automatic: true, //
//    sectionModal: true, //
    sourceSectionID: null,
    targetSectionID: null,
    defects: true,
    removed: true,
    unclassified: true,
  },
  computed: {
        getResults: function() {
//            var retList = [];
//            if (this.value) {
//                retList = retList.concat(this.valueresults);
//            }
//            if (this.section) {
//                retList = retList.concat(this.sectionresults);
//            }
//            if (this.attribute) {
//                retList = retList.concat(this.attributeresults);
//            }
//            return retList;
            return this.results.filter(function(res) {
                return ((res.resultType == "ATTRIBUTEMISMATCH" && app.attribute) || (res.resultType == "VALUEMISMATCH" && app.value) || (res.resultType == "SECTIONMATCHNOTFOUND" && app.section));
            })
        },
        getFilteredResults: function() {
            var self = this;
            var list = this.getResults;
            var list1 = list.filter(function(res) {
                return (!res.removed || app.removed);})
            var list2 = list1.filter(function(res) {
                return (!res.defect || app.defects);})
            var list3 = list2.filter(function(res) {
                return (app.unclassified || (res.defect || res.removed));})
            return list3.filter(function(cust){
                return cust.output.toLowerCase().indexOf(self.search.toLowerCase())>=0;});
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
//    addDeleted: function() {
//        for (var i=0; i<this.deletedresults.length; i++) {
//            this.results.push(this.deletedresults[i]);
//            if (this.deletedresults[i].resultType == "VALUEMISMATCH") {
//                this.valueresults.push(this.deletedresults[i]);
//            } else if (this.deletedresults[i].resultType == "SECTIONMATCHNOTFOUND") {
//                this.sectionresults.push(this.deletedresults[i]);
//            } else if (this.deletedresults[i].resultType == "ATTRIBUTEMISMATCH") {
//                this.attributeresults.push(this.deletedresults[i]);
//            }
//        }
//        this.deletedresults = []
//    },
    clickInstructions: function() {
        this.displayInstructions = true;
        this.displayCompare = false;
    },
    clickCompare: function() {
        this.displayInstructions = false;
        this.displayCompare = true;
    },
//    setAutomatic: function() {
//        this.automatic = true;
//    },
    setManual: function() {
        this.automatic = false;
    },
    closeEveryNode: function() {
        closeAllNodes(this.sourceTreeData);
        closeAllNodes(this.targetTreeData);
    },
    resultcount: function(type) {
        var count = 0
        for (var i=0; i<app.results.length; i++) {
            if (app.results[i].resultType == type) {
                count++;
            }
        }
        return count;
    },
    defectcount: function() {
        var count = 0
        for (var i=0; i<app.results.length; i++) {
            if (app.results[i].defect) {
                count++;
            }
        }
        return count;
    },
    discardedcount: function() {
        var count = 0
        for (var i=0; i<app.results.length; i++) {
            if (app.results[i].removed) {
                count++;
            }
        }
        return count;
    },
    unclassifiedcount: function() {
        var count = 0
        for (var i=0; i<app.results.length; i++) {
            if (!app.results[i].removed && !app.results[i].defect) {
                count++;
            }
        }
        return count;
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