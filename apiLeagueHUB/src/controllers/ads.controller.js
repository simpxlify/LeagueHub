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


exports.listAds = async (req, res) => {
    await pool1Connect;
    try {
        const request = pool1.request();
        var scriptSQL = `SELECT * FROM ADS` ;
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


exports.addAds = async (req, res, next) => {
    const { idAds, title_ads, txt_ads} = req.body;
    await pool1Connect;
      try {
          const request = pool1.request();
          var scriptSQL = `INSERT INTO ADS (idAds,title_ads,txt_ads) VALUES (`+idAds+`,'`+title_ads+`','`+txt_ads+`')`;
          console.log(scriptSQL);
          const result = await request.query(scriptSQL);
          res.status(201).send({mensagem: "Torneio registado com sucesso!"});
      } catch (err) {
          console.error('SQL error', err);
          res.status(500).send({mensagem: "Erro na conexão BD"})
      }
};

//  < ========================================================================================================================= >

exports.delAdsbyId = async (req, res) => {
    const id = parseInt(req.params.id);
    await pool1Connect;
    try {
        const request = pool1.request();
        var scriptSQL = `DELETE FROM ADS WHERE idAds=` +id ;
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
