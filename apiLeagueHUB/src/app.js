const express = require('express');
const cors = require('cors');
const app = express();

//  < ========================================================================================================================= >

// ==> API Routes:
const index = require('./routes/index');
const jogadoresRoute = require('./routes/jogadores');
const sedesRoute = require('./routes/sedes');
const torneiosRoute = require('./routes/torneios');
const equipasRoute = require('./routes/equipas');
const jogosRoute = require('./routes/jogos');
const adsRoute = require('./routes/ads');
const authenticateRoute = require('./routes/authentication');


//  < ========================================================================================================================= >

app.use(express.urlencoded({ extended: true }));
app.use(express.json());
app.use(express.json({ type: 'application/vnd.api+json' }));
app.use(cors());
app.use(index);

app.use('/auth/', authenticateRoute);
app.use('/api/', jogadoresRoute);
app.use('/api/', equipasRoute);
app.use('/api/', torneiosRoute);
app.use('/api/', sedesRoute);
app.use('/api/', jogosRoute);
app.use('/api/', adsRoute);






//  < ========================================================================================================================= >

module.exports = app;