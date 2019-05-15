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
    template: '<button type="button" class="btn btn-success" v-on:click="compareDocuments()">Compare Documents</button>',
    methods: {
        compareDocuments() {
            axios
                .get('/api/analysis/compare')
                .then(response => {
                    app.results = response.data;
                 })
                .catch(error => console.log(error));
        }
    }

});

Vue.component('result-list', {
  props: ['output', 'sourceid', 'targetid'],
  template: '<button type="button" class="list-group-item list-group-item-action" v-on:click="openTrees()">{{ output }}</button>',
  methods: {
    openTrees() {

        //go through source data recursively
        var source = [app.sourceTreeData]
        while(source.length > 0) {
            var node = source.shift();
            if (node.id == this.sourceid) {
                node.open = !node.open;
            }
            if (node.children) {
                for (var j=0; j<node.children.length; j++) {
                    source.push(node.children[j]);
                }
            }
        }

        //go through target data recursively
//        for (var i=0; i<app.results.length; i++) {
//            if (app.results[i].sourceid == this.sourceid) {
//                app.results[i].open = !app.results[i].open;
//            }
//            if (app.results[i].targetid == this.targetid) {
//                app.results[i].open = !app.results[i].open;
//            }
//        }
        console.log(this.sourceid + " vs " + this.targetid);

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
    all: true,
    value: false,
    section: false,
    attribute: false,
    results: [],
    sourceTreeData: {name: "Please load the source document", children: [], attribute: 0},
    targetTreeData: {name: "Please load the target document", children: [], attribute: 0}
  },
  computed: {
        getResults: function() {
            if (this.all) {
                 return this.results;

            } else {
                if (this.results !== null) {
                    var lis = []
                    for(var i = 0; i < this.results.length; i++) {
                        if (this.results[i].resultType == "ATTRIBUTEMISMATCH" && this.attribute) {
                            lis.push(this.results[i]);
                        }

                        if (this.results[i].resultType == "VALUEMISMATCH" && this.value) {
                            lis.push(this.results[i]);
                        }

                        if (this.results[i].resultType == "SECTIONMISMATCH" && this.section) {
                            lis.push(this.results[i]);
                        }

                    }
                    return lis;

                }
                return [];
            }
        }
  }
});