

//new Vue({
//    el: '#exampleHello',
//    data: { hello: 'Hello World!' }
//});



var exampleSource = new Vue({
    el: '#exampleSource',
    data: { xml: 'Hello World! Source NEW' },
    methods: {
        onFileChange(e) {
//            var fileInput = e.target.files[0];
            this.xml = e.target.files[0];
            var formData = new FormData();
            formData.append('file', this.xml);
//            axios
//                .post('/api/analysis/readSource', this.xml, null)
//                .then(response => {this.xml = response.data})
//                .catch(error => console.log(error));
            axios.post('/api/analysis/readSource',
                      formData, {
                        headers: {
                          'Content-Type': 'multipart/form-data'
                        }
                      }
                    ).then(response => {this.xml = response.data})
                    .catch(function () {
                      console.log('FAILURE!!');
                    });
            compareButton.sourceReceived = 1;
        }
    }
});

Vue.component("file-load", {
    el: '#exampleSource',
    data: function() {
        return { xml: 'Hello World! Source NEW' }
    },
    methods: {
        onFileChange(e) {
//            var fileInput = e.target.files[0];
            this.xml = e.target.files[0];
            var formData = new FormData();
            formData.append('file', this.xml);
//            axios
//                .post('/api/analysis/readSource', this.xml, null)
//                .then(response => {this.xml = response.data})
//                .catch(error => console.log(error));
            axios.post('/api/analysis/readSource',
                      formData, {
                        headers: {
                          'Content-Type': 'multipart/form-data'
                        }
                      }
                    ).then(response => {this.xml = response.data})
                    .catch(function () {
                      console.log('FAILURE!!');
                    });
            compareButton.sourceReceived = 1;
        }
    },
    template: '<b-container class="text-left"><b-form-file v-model="file" :state="Boolean(file)" placeholder="Select Source Document..." drop-placeholder="Drop Source Document here..." accept=".xml" @change="onFileChange" class="item":model="xml"></b-form-file></b-container>'
});

var exampleTarget = new Vue({
    el: '#exampleTarget',
    data: { xml: 'Hello World! Target NEW' },
    methods: {
        onFileChange(e) {
//            this.fileInput = e.target.files[0];
            this.xml = e.target.files[0];
            var formData = new FormData();
            formData.append('file', this.xml);
            axios.post('/api/analysis/readTarget',
                      formData, {
                        headers: {
                          'Content-Type': 'multipart/form-data'
                        }
                      }
                    ).then(response => { this.xml = response.data })
                    .catch(function () {
                      console.log('FAILURE!!');
                    });
            compareButton.targetReceived = 1;
        }
    }
});

var compareButton = new Vue({
    el: "#compareButton",
    data: { responses: 'Response', sourceReceived: 0, targetReceived: 0 },
    computed: {
        isEnabled: function() {
            return this.sourceReceived && this.targetReceived;
        }
    },
    methods: {
        compareDocuments: function() {
            axios
                .get('/api/analysis/compare')
                .then(response => {
                    resultList.results = response.data;
                 })
                .catch(error => console.log(error));
        }
    }
});

var resultList = new Vue({
    el: "#resultList",
    data: { results: null, all: false, value: false, section: false, attribute: false, ifcountflag: 0, elsecountflag: 0 },
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
    },
});

//var sourceTreeDisplay = new Vue({
//    el: '#sourceTreeDisplay',
//    data: { tree: getTree() }
//});
//
//var targetTreeDisplay = new Vue({
//    el: '#targetTreeDisplay',
//    data: { tree: getTree() }
//});

// define the item component
Vue.component('item', {
  template: '#item-template',
  props: {
    model: Object
  },
  data: function () {
    return {
      open: false
    }
  },
  computed: {
    isFolder: function () {
      return (this.model.children &&
        this.model.children.length)
    },
    isAttribute: function() {
      return this.model.attribute
    }
  },
  methods: {
    toggle: function () {
      if (this.isFolder) {
        this.open = !this.open
      }
    },
    changeType: function () {
      if (!this.isFolder) {
        Vue.set(this.model, 'children', [])
        this.open = true
      }
    },
  }
});


//jQuery////////////////////////////////////////////////////////
$('#all').change(function(e) {
  if (e.currentTarget.checked) {
  $('.rows').find('input[type="checkbox"]').prop('checked', true);
} else {
    $('.rows').find('input[type="checkbox"]').prop('checked', false);
  }
});