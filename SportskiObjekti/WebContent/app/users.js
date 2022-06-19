var app = new Vue({
	el: '#users',
	data: {
		products: null,
		title: "Korisnici",
		mode: "BROWSE",
		product : { username: null, password: null, name: null, last_name: null, gender: null, birthDate: null },
		error: '',
	},
	 template: ` 
    	<div>
    		<h3>Prikaz korisnika</h3>
    		<table border="1">
	    		<tr bgcolor="lightgrey">
	    			<th>Username</th>
	    			<th>Pass</th>
	    			<th>Ime</th>
	    			<th>Prezime</th>
	    			<th>Pol</th>
	    			<th>Datum rodjenja</th>
	    		</tr>
	    			
	    		<tr v-for="(p, index) in products">
	    			<td>{{p.username}}</td>
	    		 	<td>{{p.password}}</td>
	    		 	<td>{{p.name}}</td>
	    		 	<td>{{p.last_name}}</td>
	    		 	<td>{{p.gender}}</td>
	    		 	<td>{{p.birthDate}}</td>
	    		</tr>
	    	</table>
	    	<button v-on:click = "register">Registrujte se</button>
	    	<button v-on:click = "login">Ulogujte se</button>
    		<form id="forma" v-bind:hidden="mode!='CREATE'" @submit='createUser'>
			<table>
				<tr>
					<td>Username</td>
					<td><input v-bind:disabled="mode!='CREATE'" type="text" v-model = "product.username" name="username" ></td>
				</tr>
				<tr>
					<td>Pass</td>
					<td><input type="text" name="password" v-model = "product.password"></td>
				</tr>
				<tr>
					<td>Ime</td>
					<td><input type="text" name="name" v-model = "product.name"></td>
				</tr>
				<tr>
					<td>Prezime</td>
					<td><input type="text" name="last_name" v-model = "product.last_name"></td>
				</tr>
				<tr>
					<td>Pol</td>
					<td><input type="text" name="gender" v-model = "product.gender"></td>
				</tr>
				<tr>
					<td>Datum rodjenja</td>
					<td><input type="text" name="birthDate" v-model = "product.birthDate"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Pošalji"></td>
				</tr>
			</table>
			</form>
			<form id="formlogin" v-bind:hidden="mode!='LOGIN'" @submit='loginUser'>
			<table>
				<tr>
					<td>Username</td>
					<td><input v-bind:disabled="mode!='LOGIN'" type="text" v-model = "product.username" name="username" ></td>
				</tr>
				<tr>
					<td>Pass</td>
					<td><input type="text" name="password" v-model = "product.password"></td>
				</tr>
			</table>
			</form>
		</div>		  
    	`,
	mounted() {
		axios.get('rest/user/users')
			.then(response => (this.products = response.data))
	},
	methods: {
		register : function() {
			this.mode = 'CREATE'
    	},
		login : function() {
			this.mode = 'LOGIN'
    	},
		createUser: function (event) {
				axios.post('rest/user/register', this.product)
					.then((response) => {
						alert('Novi nalog uspešno kreiran')
						this.mode = 'BROWSE'
						axios.get('rest/user/users')
							.then(response => (this.products = response.data))
					})
		},
		loginUser: function (event) {
				axios.post('rest/user/login', this.product)
					.then((response) => {
						alert('Uspešno logovanje!')
						this.mode = 'BROWSE'
						if(response.code!=400)
						this.mode = 'BROWSE'
					})
		}
	}
});