var nameVar = "Any Name";
var nameVar = "Other Name";
console.log("namevar", nameVar);

let nameLet = "let name var";
console.log("namelet", nameLet);


const nameConst = "Const Name";
console.log("nameconst", nameConst);

//function scopings

function getName() {
    const petName = "petname";
    return petName;
}
// console.log("petName", petName);

const petName = getName();
console.log("petName", petName);

//block scoping
var fullname = "Any Full Name";
if(fullname){
    var firstname = fullname.split(' ')[0];
    console.log("firstname", firstname);

}

console.log("firstname", firstname);

