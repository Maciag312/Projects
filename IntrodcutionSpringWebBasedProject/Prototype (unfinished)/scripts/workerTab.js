Vue.prototype.$http = axios

var app = new Vue({
    el: "#workerID20",
    data: {
        lastname: 'Smith',
        name: 'Stan',
        address: 'St. Roosvelt 50/32B',
        nationality: 'Mongolian',
        description: 'Pizda',
        position: 'Cook'
    },
    methods: {
        loadWorker: function(){
         this.$http.get("http://localhost:8080/employee/getById",{params: {id: '1'}}).then(response => {
                this.lastname = response.data.lastname;
                this.name = response.data.name;
                this.address = response.data.Adress;  
                this.nationality = response.data.Nationality;      
                this.description = response.data.Description; 
                this.position = response.data.Position;
            });
        }
    }
});