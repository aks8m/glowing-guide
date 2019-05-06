

new Vue({
    el: '#exampleHello',
    data: { hello: 'Hello World!' }
});

new Vue({
    el: '#exampleSource',
    data: { xml: 'Hello World! Source' },
    methods: {
        onFileChange(e) {
            var file = e.target.files[0];
            this.xml = file;
            axios
                .post('/api/analysis/readSource', this.xml, null)
                .then(response => {this.xml = response.data})
                .catch(error => console.log(error));
            compareButton.sourceReceived = 1;
        }
    }
});

new Vue({
    el: '#exampleTarget',
    data: { xml: 'Hello World! Target' },
    methods: {
        onFileChange(e) {
            var file = e.target.files[0];
            this.xml = file;
            axios
                .post('/api/analysis/readTarget', this.xml, null)
                .then(response => {this.xml = response.data})
                .catch(error => console.log(error));
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
//                    resultList.displayResults(response.data);
                 })
                .catch(error => console.log(error));
//             resultList.displayResults();
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
//    methods: {
//        displayResults: function(response) {
////            this.results = response;
//            var newsTest = document.getElementById("displayListResults");
//            var itemsTest = response;
//            for(var i = 0; i < itemsTest.length; i++) {
//                var listItem = document.createElement("button");
//                listItem.setAttribute('type', 'button');
//                listItem.setAttribute('class','list-group-item list-group-item-action');
//                listItem.innerHTML = itemsTest[i].output;
//                newsTest.appendChild(listItem);
//               }
//        },
//    }
});

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

