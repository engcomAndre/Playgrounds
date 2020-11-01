 class Person {
    constructor(name = "Anynomous",age = 0){
        this.name = name;
        this.age = age;
    }

    getGreeting(){
        // return "Hi!!! " + this.name;
        return `Hi!! + ${this.name}`
    }

    getDescription(){
        return `${this.name} is a ${this.age} years old`;
    }

}
// const personOne = new Person("Any Name",25);

// console.log(personOne.getDescription());

// const personTwo = new Person( );

// console.log(personTwo.getDescription());

class Estudent extends Person {
    constructor(name,age,major){
        super(name,age);
        this.major = major;
    }

    hasMajor(){
        return !!this.major;
    }

    getDescription(){
        let description = super.getDescription();

        if(this.hasMajor){
            description += ` and your major is ${this.major}.`
        }

        return description;
    }
}

// const es = new Estudent("Est Name",25,'Any Course');
// console.log(es);
// console.log(es.getDescription());

// const esA = new Estudent( );
// console.log(esA);
// console.log(esA.hasMajor());

class Traveller extends Person {
    constructor(name,age,homelocation = "no home"){
        super(name,age);
        this.homelocation = homelocation;
    }
    getGreeting(){
        let greeting = super.getGreeting();
        if(this.homelocation){
            greeting += ` i'm visiting from ${this.homelocation}`;
        }      
        return greeting;           
    }
}

const tr = new Traveller("Ant tr",14,"Brazil");
console.log(tr);
console.log(tr.getGreeting());

