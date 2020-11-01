// console.log("destructuring.js");

// const person = {
//     name : "Andre",
//     age : 34,
//     location : {
//         city : "Fortaleza",
//         temp : 40,
//     }
// }

// const {name : firstName = "Anonymous" ,age} = person;

// console.log(`${firstName} is  ${age}`);

// const {city ,temp :temperature} = person.location;

// console.log(`${city} is  ${temperature} temp`);

// const book = {
//     title : "Hearts od Cards",
//     author : "Ease  Writer",
//     publisher : {
//         name : "Tokio",
//     }
// }

// const {name : publisherName = "Self-Publisher"} = book.publisher;

// console.log(`${publisherName}`);


// const address = ["Rua a 199","Anywhere","Any anywhere","13133"];

// const [street,city,state,zip] = address;

// console.log(`You Are in ${street} in ${city} of ${state} by ${zip}`);


const item = ["Coffee(hot)",'$2,00','$5,00','$8,00'];

const [itemName ,,medianPrice] = item;
console.log(`Item ${itemName} is a ${medianPrice}`);


const [itemNameA ,,,highPrice] = item;
console.log(`Item ${itemName} is a ${highPrice}`);