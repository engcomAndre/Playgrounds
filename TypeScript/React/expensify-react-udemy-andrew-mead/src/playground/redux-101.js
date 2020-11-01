import { createStore } from 'redux';


const incrementCount = ({incrementBy = 1} = {}) => ({
    type: "INCREMENT",
    incrementBy /*: typeof incrementBy === 'number' ? incrementBy : 1,*/
});

const decrementCount = ({decrementBy = 1} = {}) => ({
    type: "DECREMENT",
    decrementBy /* : typeof decrementBy === 'number' ? decrementBy : 1,*/
});

const setCount = ({setValue = 10 } = {value}) => ({
    type: "SET",
    setValue       
});

const reset = () => ({
    type: "RESET",     
});

const countReducer = ((state = { count: 0 }, action) => {
        switch (action.type) {
            case 'INCREMENT': {
                 return {
                    count: state.count + action.incrementBy,
                }
            }
            case 'DECREMENT': {
                 return {
                    count: state.count - action.decrementBy,
                }
            }
            case 'SET': { 
                return {
                    count: action.setValue,
                }
            }
            case 'RESET': {
                return {
                    count: 0,
                }
            }
            default: {
                return state;
            }
        }
    }
);


const store = createStore(countReducer);

const unsubscribe = store.subscribe(() => {
    console.log(store.getState());
});
store.dispatch(setCount(10));

store.dispatch(incrementCount(5));
store.dispatch(decrementCount(10));
store.dispatch(reset(0));

 

 


 