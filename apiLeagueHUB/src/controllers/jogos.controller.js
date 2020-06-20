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


exports.listJogos = async (req, res) => {
    const id = parseInt(req.params.id);
    await pool1Connect;
    try {
        const request = pool1.request();
        var scriptSQL = `SELECT * FROM GAMES WHERE idTournaments=` +id  ;
        const result = await request.query(scriptSQL);
        console.log(scriptSQL);
        console.dir(result.recordset);
        res.status(201).json(result.recordset);
    } catch (err) {
        console.error('SQL error', err);
        res.status(500).send({mensagem: "Erro na conexão BD"})
    }
};


exports.listJogosall = async (req, res) => {
    await pool1Connect;
    try {
        const request = pool1.request();
        var scriptSQL = `SELECT * FROM GAMES `;
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


exports.delJogosbyId = async (req, res) => {
    const id = parseInt(req.params.id);
    await pool1Connect;
    try {
        const request = pool1.request();
        var scriptSQL = `DELETE FROM GAMES WHERE idGame=` +id ;
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

exports.addJogo = async (req, res, next) => {
    const { idGame, idTeamOne, idTeamTwo,TeamOneName,TeamTwoName, GoalsTeamOne, GoalsTeamTwo, idTournaments} = req.body;
    await pool1Connect;
      try {
          const request = pool1.request();
          var scriptSQL = `INSERT INTO GAMES (idGame,idTeamOne,idTeamTwo,TeamOneName,TeamTwoName,GoalsTeamOne,GoalsTeamTwo,idTournaments) VALUES (`+idGame+`,`+idTeamOne+`,`+idTeamTwo+`,'`+TeamOneName+`','`+TeamTwoName+`',`+GoalsTeamOne+`,`+GoalsTeamTwo+`,`+idTournaments+`)`;
          console.log(scriptSQL);
          const result = await request.query(scriptSQL);
          res.status(201).send({mensagem: "Sucesso!"});
      } catch (err) {
          console.error('SQL error', err);
          res.status(500).send({mensagem: "Erro na conexão BD"})
      }
};