const square = function(x){
    return x*x;
}

const cube = (x) =>  x*x*x;

const nExpo = (number,expo) => {
    return Math.pow(number , expo);
}

const nExpo2 = (number,expo) => {return Math.pow(number , expo);
}

// console.log(square(2));
// console.log(cube(2));
// console.log(nExpo(2,5));


let fullName = 'any Full Name';

const firstName = (fullName) => {return fullName.split(' ')[0];}
const secondName = (fullName) => fullName.split(' ')[1];

 console.log("firstName",firstName(fullName));
 console.log("secondName",secondName(fullName));
  