const router = require('express-promise-router')();
const jogosController = require('../controllers/jogos.controller');

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

//delidjogo route
router.delete('/jogos/:id', jogosController.delJogosbyId);

//  < ========================================================================================================================= >

//listjogo route
router.get('/jogos', jogosController.listJogosall);
//listIdjogo route
router.get('/jogos/:id', jogosController.listJogos);

//  < ========================================================================================================================= >

router.post('/jogos/', jogosController.addJogo);


module.exports = router;