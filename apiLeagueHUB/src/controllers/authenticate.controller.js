const jwt = require('jsonwebtoken');
const sql = require('mssql');
const sqlConfig = require('../config/database');
const bcrypt = require('bcrypt');
require('dotenv').config();

//  < ========================================================================================================================= >

//Create a database connection to be used by the Methods
const pool1 = new sql.ConnectionPool(sqlConfig);
const pool1Connect = pool1.connect();

pool1.on('error', err => {
    console.log(err);
})

//  < ========================================================================================================================= >

//Method to Register a 'User':

  exports.register = async (req, res, next) => {
    const {name,username,email,password} = req.body;

    passwordHash = await bcrypt.hash(password, 5);
    await pool1Connect;
      try {
          const request = pool1.request();
          var scriptSQL = `insert into Users (name, username, email, password) values ('` + name + `','` + username + `','` + email + `','` + passwordHash +`')`;
          const result = await request.query(scriptSQL);
          console.log(scriptSQL);
          res.status(201).send({message: "User registered successfully"});
      } catch (err) {
          console.error('SQL error', err);
          res.status(500).send({message: "Database connection Error"})
      }
  };

//  < ========================================================================================================================= >

  //Method to do a 'Login':

  exports.login = async (req, res, next) => {
    const { username, password } = req.body;
    await pool1Connect;
      try {
          const request = pool1.request();
          var scriptSQL = `select username, password from Users where username='` + username + `'`;
          const result = await request.query(scriptSQL);
          console.log(scriptSQL);

          if(result.recordset.length==0){
            res.status(400).send({message: "User not registered in the System"})
            return;
          }

          const usernameDatabase = result.recordset[0].username;
          const passwordDatabase = result.recordset[0].password;

          if(!await bcrypt.compare(password, passwordDatabase)){
              res.status(400).send({message: "Wrong Password"})
              return;
          }

          var token = jwt.sign({ id: usernameDatabase }, process.env.SECRET_API, {
            expiresIn: 300 //expires in 5 minutes
          });
          
          res.status(200).send({ auth: true, token: token });
          return;

      } catch (err) {
          console.error('SQL Error', err);
          res.status(500).send({message: "Database connection Error"})
      }
  };

//  < ========================================================================================================================= >

exports.renewToken = async (req, res, next) => {

    try {
        const usernameDatabase = req.userId;
        var token = jwt.sign({ id: usernameDatabase }, process.env.SECRET_API, {
          expiresIn: 300
        });
        
        res.status(200).send({ auth: true, token: token });
        return;

    } catch (err) {
        console.error('SQL Error', err);
        res.status(500).send({message: "Database connection Error"})
    }
};

//  < ========================================================================================================================= >

exports.Listusers= async (req, res) => {
  await pool1Connect;
  try {
      const request = pool1.request();
      var scriptSQL = `SELECT * FROM USERS` ;
      const result = await request.query(scriptSQL);
      console.log(scriptSQL);
      console.dir(result.recordset);
      res.status(201).json(result.recordset);
  } catch (err) {
      console.error('SQL error', err);
      res.status(500).send({mensagem: "Erro na conexão BD"})
  }
};

//  < ========================================================================================================================= >

exports.delUsersbyId = async (req, res) => {
  const id = parseInt(req.params.id);
  await pool1Connect;
  try {
      const request = pool1.request();
      var scriptSQL = `DELETE FROM USERS WHERE id=` +id ;
      const result = await request.query(scriptSQL);
      console.log(scriptSQL);
      console.dir(result.recordset);
      res.status(201).json(result.recordset);
  } catch (err) {
      console.error('SQL error', err);
      res.status(500).send({mensagem: "Erro na conexão BD"})
  }
};

//  < ========================================================================================================================= >

exports.recuperarPassword = async (req, res) => {
  const username = req.params.username;
  const { password } = req.body;
  passwordHash = await bcrypt.hash(password, 5);
  await pool1Connect;
  try {
      const request = pool1.request();
      var scriptSQL = `update USERS set password = '` + passwordHash + `' where username = '` + username + `'`; 
      const result = await request.query(scriptSQL);
      console.log(scriptSQL);
      res.status(201).send({mensagem: "Password Recuperada"});
  } catch (err) {
      console.error('SQL error', err);
      res.status(500).send({mensagem: "Erro na conexão BD"})
  }
};