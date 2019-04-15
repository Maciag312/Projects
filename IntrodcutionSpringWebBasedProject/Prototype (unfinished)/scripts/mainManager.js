
simpleTask = Vue.component("simple-task",{
    props: ["taskData"],
    template: "#simpleTaskTemplate"
});

simpleAlert = Vue.component("simple-alert",{
    props: ["alertData"],
    template: "#simpleAlertTemplate",
    computed:{
        seenClass: function(){
            if(this.alertData.seen)
                return "alertSeen";
            else
                return null;
        }
    }
});

workerTab = Vue.component("worker-tab",{
    props: ["employee"],
    template: "#workerTabTemplate",
    components: {
        simpleTask: simpleTask,
        simpleAlert: simpleAlert
    },
    data: function (){ return {
        employeeTasks: [],
        employeeAlerts: []
    }; },
    methods: {
        updateTasks: function(){
            this.employeeTasks = [];
            for (var i = 0; i < this.employee.tasks.length; i++){ // NA RAZIE WYSZUKIWANIE LINIOWE
                for(var z = 0; z < this.$parent.tasks.length; z++){
                    if(this.$parent.tasks[z].id == this.employee.tasks[i]){
                        this.employeeTasks.push(this.$parent.tasks[z]);
                    }
                }
            }
        },
        updateAlerts: function(){
            this.employeeAlerts = [];
            for (var i = 0; i < this.employee.alerts.length; i++){ // NA RAZIE WYSZUKIWANIE LINIOWE
                for(var z = 0; z < this.$parent.alerts.length; z++){
                    if(this.$parent.alerts[z].id == this.employee.alerts[i]){
                        this.employeeAlerts.push(this.$parent.alerts[z]);
                    }
                }
            }
        }
    },
    created: function(){
        this.updateTasks();
        this.updateAlerts();
        
    },
    watch:{
        "employee.tasks": function (newEmployee, oldEmployee) {
            console.log("aaaaaaaaaaaaaaaaa");
            this.updateTasks();
        },
        "employee.alerts": function (newEmployee, oldEmployee) {
            this.updateAlerts();
        }
    }
});

app = new Vue({
    el: "#omniContainer",
    data: {
        alerts: [
            {
                id: 12,
                date: "2015-05-21",
                name: "Zesrałem się",
                state: "alertUrge",
                seen: false
            },
            {
                id: 14,
                date: "2014-06-21",
                name: "Co kurwa???",
                state: "alertWarning",
                seen: false
            },
            {
                id: 15,
                date: "2025-03-26",
                name: "Zesrałem się 2",
                state: "alertSuccess",
                seen: true
            },
            {
                id: 19,
                date: "2018-10-30",
                name: "Kupiłem se kebsa",
                state: "alertSuccess",
                seen: false
            },
            {
                id: 20,
                date: "2017-11-05",
                name: "Krym jest nasz",
                state: "alertWarning",
                seen: true
            }
        ],
        tasks: [
            {
                id: 25,
                startHour: "18:30",
                endHour: "20:00",
                date: "2013-02-06",
                title: "Go to the basement XD"
            },
            {
                id: 26,
                startHour: "04:15",
                endHour: "15:00",
                date: "2016-02-06",
                title: "Attack the enemy!"
            },
            {
                id: 27,
                startHour: "21:30",
                endHour: "21:45",
                date: "2137-02-06",
                title: "Ressurect the pope"
            },
            {
                id: 28,
                startHour: "00:00",
                endHour: "02:00",
                date: "2020-01-01",
                title: "Let's rumble the party"
            }
        ],
        employees: [
            {
                loadedAllData: true, // this will be a computed variable!
                displayedTab: true,
                lastname: 'Smith',
                name: 'Stan',
                address: 'St. Roosvelt 50/32B',
                nationality: 'Mongolian',
                city: "Bubaland, Poland",
                pesel: "39393901010",
                phoneNumber: '111 111 111',
                accountNumber: "6858 2330 2393 3145",
                email: "dupa@haha.pl",
                description: 'He loves to suck cocks like mad!',
                position: 'Cook',
                actualContract: {
                    id: 12,
                    name: "Contract 2"
                },
                actualShift: {
                    id: 52,
                    name: "Morning Shift"
                },
                tasks: [25,28],
                alerts: [12,15],
                id: 20
            },
            {
                loadedAllData: true,
                displayedTab: true,
                lastname: 'Anderson',
                name: 'Michel',
                address: 'Main Street 2137',
                nationality: 'Catalonian',
                city: "Canton, PRC",
                pesel: "39393901022",
                phoneNumber: '111 111 333',
                accountNumber: "9858 7521 2201 7045",
                email: "dupa2@haha.pl",
                description: 'What the fuck?',
                position: 'CEO',
                actualContract: {
                    id: 22,
                    name: "Special Contract"
                },
                actualShift: {
                    id: 52,
                    name: "Morning Shift"
                },
                tasks: [],
                alerts: [14,15],
                id: 30
            },
            {
                loadedAllData: true,
                displayedTab: true,
                lastname: 'Buba',
                name: 'Huba',
                address: 'Bombastic 2020/2900',
                nationality: 'Marsian',
                city: "Warsaw, Poland",
                pesel: "39301002299",
                phoneNumber: '111 111 112',
                accountNumber: "8808 0221 1111 0000",
                email: "kebab@aaa.org",
                description: 'What is even him?',
                position: 'Alien',
                actualContract: {
                    id: 12,
                    name: "Contract 2"
                },
                actualShift: {
                    id: 18,
                    name: "Replacement Shift"
                },
                tasks: [25,26,27],
                alerts: [19,20],
                id: 42
            },
            {
                loadedAllData: false,
                displayedTab: false,
                lastname: 'Paweł',
                name: 'Jan',
                address: '',
                nationality: '',
                city: "",
                pesel: "",
                phoneNumber: '',
                accountNumber: "",
                email: "",
                description: '',
                position: '',
                actualContract: {
                    id: null,
                    name: ""
                },
                actualShift: {
                    id: null,
                    name: ""
                },
                tasks: [],
                alerts: [],
                id: 200
            },
            {
                loadedAllData: false,
                displayedTab: false,
                lastname: 'Łuszcz',
                name: 'Piotr',
                address: '',
                nationality: '',
                city: "",
                pesel: "",
                phoneNumber: '',
                accountNumber: "",
                email: "",
                description: '',
                position: '',
                actualContract: {
                    id: null,
                    name: ""
                },
                actualShift: {
                    id: null,
                    name: ""
                },
                tasks: [],
                alerts: [],
                id: 213
            },
            {
                loadedAllData: false,
                displayedTab: false,
                lastname: 'Alibaba',
                name: 'Kali',
                address: '',
                nationality: '',
                city: "",
                pesel: "",
                phoneNumber: '',
                accountNumber: "",
                email: "",
                description: '',
                position: '',
                actualContract: {
                    id: null,
                    name: ""
                },
                actualShift: {
                    id: null,
                    name: ""
                },
                tasks: [],
                alerts: [],
                id: 2137
            },
        ]
    },
    components: {
        workerTab: workerTab
    }
});



