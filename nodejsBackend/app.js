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

	res.send(wallet)

})

app.post('/addwalletandroid', async(req,res)=>{ 

	wallet.push(req.body.nameValuePairs);
	
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

 
	var obj = req.body;
	console.log(obj);
	// var wallet = await Wallet.findOne()

	var arr2 = wallet.filter(async(elmnt) => { return elmnt.id.indexOf(obj.id) > -1; });

	// var arr2 = wallet.filter(async(elmnt) =>{ console.log(elmnt) });
	// var arr2 = arr.filter(function(elmnt) { return elmnt.indexOf("a") > -1; }).push("aaa");

	// var master = await Wallet.findOne({},function(err,wallet){
	//   wallet.details.push(obj.nameValuePairs)
	//   wallet.save(function(err,m){
	// 	console.log("Master Comment Updated");
	//   })
	// })

	console.log(arr2);

	res.send(req.body);
   
})


mongoose.connect('mongodb://localhost:27017/eltendedero',(err)=>{
    if(!err){
        console.log('Connected to mongo Database');
    }
})

app.listen(8082);
