console.log("App is run");
// alert("Running");

var app = {
    title: 'Indecision App',
    subtitle: "Put your life in computer  hands...",
    options: []
}

const onFormSubmit = (event) => {
    event.preventDefault();
    console.log(event.target.elements.option.value);
    let option = event.target.elements.option.value;
    if (option) {
        app.options.push(option);
        renderApp();
        event.target.elements.option.value = "";

    }
}

const onRemoveAll = (event) => {
    event.preventDefault();
    if (app.options && app.options.length > 0) {
        app.options = [];
        renderApp();
    }
}

const onMakeDecision = (event) => {
    const randNumber = Math.floor(Math.random() * app.options.length);
    const option = app.options[randNumber]
    alert(option);asd
}

var appRoot = document.getElementById("app");
// ReactDOM.render(template,appRoot);   

const renderApp = () => {
    var template = (
        <div>
            <h1>{app.title}</h1>
            <p>{app.subtitle}</p>
            <p>{app.options.length > 0 ? "Here Your Options" : "no Options"}</p>
            <button disabled={app.options.length < 1} onClick={onMakeDecision}>What should all</button>
            <button onClick={onRemoveAll}>Remove All</button>
            <ol>
                {app.options.map((option) => <li id={app.options.indexOf(option)}>{option}</li>)}
            </ol>
            <form onSubmit={onFormSubmit}>
                <input type="text" name="option" />
                <button>Add Option</button>
            </form>
        </div>
    );
    ReactDOM.render(template, appRoot);

}

renderApp();












//eas to read code
// var template = (
//     <div>
//         <h1>{app.title}</h1>
//         <p>{app.subtitle}</p>
//         <p>{app.options.length > 0 ? "Here Your Options" : "no Options"}</p>
//         <ol>
//             <li>Item One</li>
//             <li>Item Two</li>
//             <li>Item Tree</li>
//         </ol>
//         <form onSubmit={onFormSubmit}>
//             <input type="text" name="option"/>
//             <button>Add Option</button>            
//         </form>
//     </div>
// );


