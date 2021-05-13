var express = require('express');
var bodyParser = require('body-parser');
var app = express();
app.use(express.static('static'))
app.use(bodyParser.json());
var User = require('./models/user.js');
var Wallet = require('./models/wallet.js');
var mongoose = require('mongoose');
var userController = require('./controller/userController');


/*********
*   User Controller
*
**********/

var wallet = [{
	"id":"1",
	"name":"title",
	"details":[],
	"user": "reymesson"
}];
app.post('/login', userController.setLogin);

app.get('/wallet', async(req,res)=>{

	// console.log(req.body.nameValuePairs.name)

	// var wallet = await Wallet.find()
	// var wallet = await Wallet.find({"name":req.body.nameValuePairs.name})

	console.log(wallet)

	res.send(wallet)

})
           
app.post('/addwalletandroid', async(req,res)=>{ 

	var obj = {
		id : req.body.nameValuePairs.id,
		date : req.body.nameValuePairs.date,
		name: req.body.nameValuePairs.name,
		details: req.body.nameValuePairs.details.values,
		user: req.body.nameValuePairs.user
	}

	wallet.push(obj);

	console.log(obj)
	
	console.log(req.body.nameValuePairs.details.values);
	console.log(req.body.nameValuePairs);

	// var wallet = new Wallet(req.body.nameValuePairs); 
	// wallet.save(function(err){ 

	// 	if(!err){ 
	// 		console.log('Wallet saved');
	// 	 }
	//  })
	//  console.log(req.body.nameValuePairs)
	 res.send(req.body)
})

app.post('/addwalletandroidcomment', async(req,res) =>{

 
	var obj = req.body.nameValuePairs;
	console.log(obj);
	// var wallet = await Wallet.findOne()

	var arr2 = wallet.filter(async(elmnt) => { return elmnt.id.indexOf(obj.id) > -1; })[0].details.push(obj)

	console.log(arr2);

	res.send(req.body);
   
})


mongoose.connect('mongodb://localhost:27017/eltendedero',(err)=>{
    if(!err){
        console.log('Connected to mongo Database');
    }
})

app.listen(8082);
