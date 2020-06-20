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


exports.listEquipas = async (req, res) => {
    await pool1Connect;
    try {
        const request = pool1.request();
        var scriptSQL = `SELECT * FROM TEAMS` ;
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


exports.addEquipas = async (req, res, next) => {
    const { idTeam, name, initials, location, coach} = req.body;
    await pool1Connect;
      try {
          const request = pool1.request();
          var scriptSQL = `INSERT INTO Teams (idTeam,name,initials,location,coach) VALUES (`+idTeam+`,'`+name+`','`+initials+`','`+location+`','`+coach+`')`;
          console.log(scriptSQL);
          const result = await request.query(scriptSQL);
          res.status(201).send({mensagem: "Equipa criada com sucesso!"});
      } catch (err) {
          console.error('SQL error', err);
          res.status(500).send({mensagem: "Erro na conexão BD"})
      }
};

//  < ========================================================================================================================= >


exports.delEquipasbyId = async (req, res) => {
    const id = parseInt(req.params.id);
    await pool1Connect;
    try {
        const request = pool1.request();
        var scriptSQL = `DELETE FROM Teams WHERE idTeam=` +id ;
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


exports.listIdEquipas = async (req, res) => {
    const id = parseInt(req.params.id);
    await pool1Connect;
    try {
        const request = pool1.request();
        var scriptSQL = `SELECT * FROM TEAMS WHERE idTeam=` +id ;
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


exports.addEquipasNoTorneio = async (req, res, next) => {
    const { idTeam, name, initials, location, coach, idTournaments } = req.body;
    await pool1Connect;
      try {
          const request = pool1.request();
          var scriptSQL = `INSERT INTO Teams (idTeam,name,initials,location, coach,idTournaments) VALUES (`+idTeam+`,'`+name+`','`+initials+`','`+location+`','`+coach+`',`+idTournaments+`)`;
          console.log(scriptSQL);
          const result = await request.query(scriptSQL);
          res.status(201).send({mensagem: "Equipa criada com sucesso!"});
      } catch (err) {
          console.error('SQL error', err);
          res.status(500).send({mensagem: "Erro na conexão BD"})
      }
};