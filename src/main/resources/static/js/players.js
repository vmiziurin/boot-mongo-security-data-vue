var playerApi = Vue.resource('/api/players{/id}')

Vue.component('player-form', {
    props: ['players' , 'playerToEdit'],
    data: function() { return {id: '', firstName: '', lastName: '', number: ''} },
    template:
        '<thead>' +
            '<th><input type="text" placeholder="First Name" v-model="firstName"></th>' +
            '<th><input type="text" placeholder="Last Name" v-model="lastName"></th>' +
            '<th><input type="number" placeholder="Number" v-model="number"></th>' +
            '<th><input type="button" value="Add" @click="save" /></th>' +
            '<th>Delete</th>' +
        '</thead>',
    watch: {
        playerToEdit: function(newVal, oldVal) {
            this.id = newVal.id
            this.firstName = newVal.firstName
            this.lastName = newVal.lastName
            this.number = newVal.number
        }
    },
    methods: {
        save: function(){
            var player = {firstName: this.firstName, lastName: this.lastName, number: this.number}
            if (this.id) {
                playerApi.update({id : this.id}, player).then(result =>result.json().then(player => {
                    this.players.splice(this.players.indexOf(this.playerToEdit), 1, player)
                    this.id = ''
                }))
            } else {
                playerApi.save(player).then(result => {
                    result.json().then(player => this.players.push(player))
                })
            }
            this.firstName = ''
            this.lastName = ''
            this.number = ''
        }
    }
});

Vue.component('player-rows', {
  props: ['player', 'players', 'editPlayer'],
  template:
    '<tr>' +
        '<td>{{ player.firstName }}</td>' +
        '<td>{{ player.lastName }}</td>' +
        '<td>{{ player.number }}</td>' +
        '<td><span><input type="button" value="Edit" @click="edit" /></span></td>' +
        '<td><span><input type="button" value="X" @click="del" /></span></td>' +
    '</tr>',
    methods: {
        edit: function() { this.editPlayer(this.player) },
        del: function() { playerApi.remove({id: this.player.id}).then(result => {
            if(result.ok) {
                this.players.splice(this.players.indexOf(this.player), 1)
            }
        })}
    }
});

Vue.component('players-list', {
  props: ['players', 'profile'],
  data: function() {return {playerToEdit:null}},
  template:
  '<div>' +
    '<table border="1">' +
        '<player-form v-if="this.profile" :players=players :playerToEdit="playerToEdit"/>' +
        '<tbody><player-rows v-for="player in this.players" :key="player.id" :player="player" :players="players" :editPlayer="editPlayer"/></tbody>' +
    '</table>' +
  '</div>',
  methods:{
    editPlayer: function(player){ this.playerToEdit = player }
  }
});

var app = new Vue({
    el: '#app',
    template:
        '<div>' +
            '<div v-if="profile">' +
                '{{ profile.username }} <a href="/logout">Logout</a>' +
            '</div>' +
            '<div v-else>' +
                '<a href="/login">Login</a>' +
            '</div>' +
             '<div><players-list :players=players :profile=profile /></div>' +
        '</div>',
    data: {
        players: frontData.players,
        profile: frontData.profile
    }
});
