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


exports.listSedes = async (req, res) => {
    await pool1Connect;
    try {
        const request = pool1.request();
        var scriptSQL = `SELECT * FROM VENUES` ;
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


exports.addSedes = async (req, res, next) => {
    const { idVenues, name_venue, location_venues} = req.body;
    await pool1Connect;
      try {
          const request = pool1.request();
          var scriptSQL = `INSERT INTO VENUES (idVenues,name_venue,location_venues) VALUES (`+idVenues+`,'`+name_venue+`','`+location_venues+`')`;
          console.log(scriptSQL);
          const result = await request.query(scriptSQL);
          res.status(201).send({mensagem: "Torneio registado com sucesso!"});
      } catch (err) {
          console.error('SQL error', err);
          res.status(500).send({mensagem: "Erro na conexão BD"})
      }
};

//  < ========================================================================================================================= >

exports.delSedesbyId = async (req, res) => {
    const id = parseInt(req.params.id);
    await pool1Connect;
    try {
        const request = pool1.request();
        var scriptSQL = `DELETE FROM VENUES WHERE idVenues=` +id ;
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
