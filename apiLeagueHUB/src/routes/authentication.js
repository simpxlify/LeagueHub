const router = require('express-promise-router')();
const authenticateController = require('../controllers/authenticate.controller');

//  < ========================================================================================================================= >

//Method to validate the Token:

const jwt = require('jsonwebtoken');
require('dotenv').config();

function verifyJWT(req, res, next){
    var token = req.headers['x-access-token'];
    if (!token) return res.status(401).send({ auth: false, message: 'No token provided.' });
    
    jwt.verify(token, process.env.SECRET_API, function(err, decoded) {
      if (err) return res.status(500).send({ auth: false, message: 'Failed to authenticate token.' });
      
      //If it is Ok the process is saved in the request to be used after
      req.userId = decoded.id;
      next();
    });
};

//  < ========================================================================================================================= >

//Authentication route
router.post('/register', authenticateController.register);

//  < ========================================================================================================================= >

//Login route
router.post('/login', authenticateController.login);

//USERs route
router.get('/users', authenticateController.Listusers);

//USERs DEL route
router.delete('/users/:id', authenticateController.delUsersbyId);

router.put('/users/:username', authenticateController.recuperarPassword)


//  < ========================================================================================================================= >

//Renew Token route
router.get('/renewtoken', verifyJWT, authenticateController.renewToken);

//  < ========================================================================================================================= >

module.exports = router;