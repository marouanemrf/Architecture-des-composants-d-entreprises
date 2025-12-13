const fs = require('fs');
const convert = require('xml-js');
const protobuf = require('protobufjs');

// Charger la définition Protobuf depuis employee.proto
const root = protobuf.loadSync('employee.proto');
const EmployeeList = root.lookupType('Employees');

// Construire la liste d'employés
const employees = [];

employees.push({
  id: 1,
  name: 'Ali',
  salary: 9000
});

employees.push({
  id: 2,
  name: 'Kamal',
  salary: 22000
});

employees.push({
  id: 3,
  name: 'Amal',
  salary: 23000
});

// Objet racine compatible avec message Employees
let jsonObject = { employee: employees };

// ---------- JSON ----------

// Sérialisation en JSON
let jsonData = JSON.stringify(jsonObject);

// ---------- XML ----------

// Options de conversion JSON -> XML
const options = {
  compact: true,
  ignoreComment: true,
  spaces: 0
};

// Conversion en XML, avec balise racine <root>
let xmlData = "<root>\n" + convert.json2xml(jsonObject, options) + "\n</root>";

// ---------- Protobuf ----------

// Vérification de conformité avec le schéma Protobuf
let errMsg = EmployeeList.verify(jsonObject);
if (errMsg) {
  throw Error(errMsg);
}

// Création du message Protobuf
let message = EmployeeList.create(jsonObject);

// Encodage en binaire Protobuf
let buffer = EmployeeList.encode(message).finish();

// ---------- Écriture des fichiers ----------

fs.writeFileSync('data.json', jsonData);
fs.writeFileSync('data.xml', xmlData);
fs.writeFileSync('data.proto', buffer);

// ---------- Mesure des tailles ----------

const jsonFileSize = fs.statSync('data.json').size;
const xmlFileSize = fs.statSync('data.xml').size;
const protoFileSize = fs.statSync('data.proto').size;

console.log(`Taille de 'data.json' : ${jsonFileSize} octets`);
console.log(`Taille de 'data.xml'  : ${xmlFileSize} octets`);
console.log(`Taille de 'data.proto': ${protoFileSize} octets`);