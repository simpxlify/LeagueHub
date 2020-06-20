const express = require('express');
const router = express.Router();

//  < ========================================================================================================================= >

router.get('/api', (req, res) => {

  res.status(200).send( {
    success: 'true',
    message: 'Welcome to LeagueHub API Developed with Node.js + MS SQL Server',
    version: '1.0.0',
  });
});

//  < ========================================================================================================================= >

module.exports = router;