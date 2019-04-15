var TitleTab = Vue.component("title-tab", {
    props: ["vtitle"],
    template: '<h2 class = "bigTitle"> {{ vtitle }} </h2>'
});
var NavBar = Vue.component("navbar", {
    props: ["tasks"],
    template: `
    <div class = "navbarMain">   
        <div class = "navbarItem" v-bind:key = "task.id" v-for = "task in tasks"> {{ task.title }}</div>
    </div>
    `
});
var AdderTab = Vue.component("adder-tab",{
    props: [],
    data: function(){
        return {
            newTaskContent: ""
        }
    },
    template: `
        <div class = "adderTab">
            <input name = "text" v-model="newTaskContent">
            <input v-on:click="emitFurther" type = "submit" value="Dodaj nowy">
        </div>
    `,
    methods:{
        emitFurther: function(){
            this.$emit('add-new',this.newTaskContent);
        }
    }
});
var taskList = Vue.component("task-list",{
    props: ["tasks"],
    template: `
    <div class = "taskList">   
        <div class = "taskListItem" v-bind:key = "task.id" v-for = "task in tasks"> 
            <h5> {{task.title}} </h5>
            <article>
                {{task.content}}
            </article>
        </div>
    </div>
    `
});
var myApplication = new Vue({
  el: "#myApp",
  data: {
    Tasks: [{ id: 0, title: "Clean my bedroom", content: "Sed hendrerit venenatis commodo. Curabitur fringilla metus at ex faucibus, in aliquet ex porta. Nunc in arcu id urna mollis viverra. Quisque consequat malesuada sem ut tempus. Mauris sem mauris, viverra egestas pulvinar nec, auctor id nibh. Nam quis odio a metus finibus mollis viverra quis risus. Maecenas id elit ultrices, dictum orci eu, dapibus leo. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nunc vulputate iaculis massa ut facilisis. Mauris at dignissim felis. Proin fermentum placerat urna ac volutpat. In imperdiet, lectus sit amet convallis lacinia, nibh ante vehicula dolor, sit amet tincidunt erat nisl vel metus. Donec molestie imperdiet felis, et ultrices odio tincidunt et. Donec fermentum ultrices dignissim."},
            { id: 1, title: "Visit my beloved neighbour", content: "Duis vel fermentum dolor, id interdum urna. Donec tempus in ante eu placerat. Fusce volutpat elementum aliquam. Proin ut enim id orci interdum faucibus. Ut ac malesuada velit. Cras non gravida enim. Donec ut turpis tempor, condimentum ligula sit amet, lobortis diam."},
            { id: 2, title: "Attack the enemy's backyard", content: "Mauris metus augue, tincidunt vel iaculis at, aliquet quis nunc. Pellentesque quis lacus et arcu vulputate tincidunt eu nec turpis. Phasellus sit amet justo id lacus tempus accumsan. Donec cursus ex quis nisi volutpat laoreet. Morbi ac rutrum orci, varius eleifend erat. Cras non aliquam purus. Cras gravida metus vulputate est porttitor sagittis eu a felis. Curabitur dignissim condimentum molestie. Nulla ac est lobortis, aliquam lacus sed, mollis turpis. "}
            ],
    PageTitle: "Welcome brothers and sisters!"
    },
    components:{
        TitleTab: TitleTab,
        NavBar: NavBar  
    },
    methods:{
        onAddNew: function(content){
            console.log("aaa");
            this.Tasks.push({id: 3, title: "Just added!", content: content});
        }
    }
});

