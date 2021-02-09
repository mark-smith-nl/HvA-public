var http = require('http');
var url = require('url');
var mysql = require('mysql');

http.createServer(function (req, res) {
    res.writeHead(200, {'Content-Type': 'text/html'});

    var q = url.parse(req.url, true).query
    var txt = q.year + " " + q.month;
    res.end(txt);
}).listen(8080);

console.log('I hear a scream!');

const { Client } = require('pg');

const client = new Client({
    user: 'msmith',
    host: 'localhost',
    database: 'msmith',
    password: '25Colisa@labiosa',
    port: 5432,
});

client.connect();
const query = `
CREATE TABLE sandbox.users (
    email varchar,
    firstName varchar,
    lastName varchar,
    age int
);
`;

client.query(query, (err, res) => {
    if (err) {
        console.error(err);
        return;
    }
    console.log('Table is successfully created');
    client.end();
});

