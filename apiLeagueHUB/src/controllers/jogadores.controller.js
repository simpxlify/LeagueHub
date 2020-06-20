const sql = require('mssql');
const sqlConfig = require('../config/database');

//  < ========================================================================================================================= >

//Create a database connection to be used by the Methods
const pool1 = new sql.ConnectionPool(sqlConfig);
const pool1Connect = pool1.connect();

pool1.on('error', err => {
    console.log(err);
})

//  < ========================================================================================================================= >


exports.listJogadores = async (req, res) => {
    await pool1Connect;
    try {
        const request = pool1.request();
        var scriptSQL = `SELECT * FROM PLAYERS` ;
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


exports.addJogadores = async (req, res, next) => {
    const { idPlayer, first_name, last_name, age, height,country,position} = req.body;
    await pool1Connect;
      try {
          const request = pool1.request();
          var scriptSQL = `INSERT INTO Players (idPlayer,first_name,last_name,age,height,country,position) VALUES (`+idPlayer+`,'`+first_name+`','`+last_name+`',`+age+`,`+height+`,'`+country+`','`+position+`')`;
          console.log(scriptSQL);
          const result = await request.query(scriptSQL);
          res.status(201).send({mensagem: "Jogador registado com sucesso!"});
      } catch (err) {
          console.error('SQL error', err);
          res.status(500).send({mensagem: "Erro na conexão BD"})
      }
};

//  < ========================================================================================================================= >

exports.delJogadoresbyId = async (req, res) => {
    const id = parseInt(req.params.id);
    await pool1Connect;
    try {
        const request = pool1.request();
        var scriptSQL = `DELETE FROM PLAYERS WHERE idPlayer=` +id ;
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
exports.listIdJogadores = async (req, res) => {
    const id = parseInt(req.params.id);
    await pool1Connect;
    try {
        const request = pool1.request();
        var scriptSQL = `SELECT * FROM PLAYERS WHERE idPlayer=` +id ;
        const result = await request.query(scriptSQL);
        console.log(scriptSQL);
        console.dir(result.recordset);
        res.status(201).json(result.recordset);
    } catch (err) {
        console.error('SQL error', err);
        res.status(500).send({mensagem: "Erro na conexão BD"})
    }
};



exports.addJogadoresNaEquipa = async (req, res, next) => {

    const { idPlayer, first_name, last_name, age, height,country,position,idTeam} = req.body;
    await pool1Connect;
      try {
          const request = pool1.request();
          var scriptSQL = `INSERT INTO Players (idPlayer,first_name,last_name,age,height,country,position,idTeam) 
          VALUES (`+idPlayer+`,'`+first_name+`','`+last_name+`',`+age+`,`+height+`,'`+country+`','`+position+`',`+idTeam+`)`;
          console.log(scriptSQL);
          const result = await request.query(scriptSQL);
          res.status(201).send({mensagem: "Jogador registado com sucesso!"});
      } catch (err) {
          console.error('SQL error', err);
          res.status(500).send({mensagem: "Erro na conexão BD"})
      }
};