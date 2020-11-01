console.log("App is run");
// alert("Running");

var app = {
    title: 'Indecision App',
    subtitle: "Put your life in computer  hands...",
    options: ['one', 'two']
}
var template = <h1>This compile in the JSX...</h1>;

//eas to read code
var templateOne = (
    <div>
        <h1>Header Content</h1>
        <p>Paragraph Content</p>
        <p>{app.options.length > 0 ? "Here Your Options" : "no Options"}</p>
        <ol>
            <li>Item One</li>
            <li>Item Two</li>
            <li>Item Tree</li>
        </ol>
    </div>
);

var templateTwo = (
    <div>
        <h1>Any Name</h1>
        <p>Age : Any Age</p>
        <p>Location : Any Location</p>
    </div>
);


var name = 'Any Name';
var age = 'Any Age';
var local = 'adasdasd';

var templateTree = (
    <div>
        <h1>{name}</h1>
        <p>Age : {age}</p>
        <p>Location : {local}</p>
    </div>
);

var user = {
    name: 'Any Name',
    age: 'Any Age',
    local: 'AnyLocal'
}

var templateFour = (
    <div>
        <h1>{user.name}</h1>
        <p>Age : {user.age}</p>
        <p>Location : {user.local}</p>
    </div>
);

function getLocation() {
    return <p>Location : {user.local}</p>
}

var templateFive = (
    <div>
        <h1>{user.name}</h1>
        <p>Age : {user.age}</p>
        {getLocation()}
    </div>
);

const getName = () => {
    return <h2>{user.name}</h2>
}

var templateSix = (
    <div>
        {getName()}
        <p>Age : {user.age}</p>
        {getLocation()}
    </div>
);

// conditional statements

const anyobj = {
    name: "Name",
    age: "12",
    local: "anywhere"
}

function getAge() {
    return anyobj.age ? anyobj.age : "Not Allowed age...";
}

var templateSeven = (
    <div>
        {getName()}
        {(anyobj && (anyobj.name.length > 0)) ? <h2>{anyobj.name}</h2> : <h2>Object not allowed</h2>}
        {anyobj.age >= 18 && <p>Age : {anyobj.age}</p>}
        {getLocation()}
        {(app.options && app.options.length > 0) ?
            <div><p>Your Options</p>
                <ol>
                    <li>{app.options[0]}</li>
                    <li>{app.options[1]}</li>
                </ol>
            </div>
            : <p>Options not allowed</p>
        }
    </div>
);

let _name = "objname";
const _age = "objage";
const _local = "objage";

const obj = {
    objname: _name,
    objAge: _age,
    objLocal: _local
}

const templateEight = (
    <div>
        <h1>{obj.objname}</h1>
        <p>{obj.objAge}</p>
        <p> {obj.objLocal}  </p>
    </div>
);


var appRoot = document.getElementById("app");
ReactDOM.render(templateEight, appRoot);
