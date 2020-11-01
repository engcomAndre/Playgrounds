import { createStore, combineReducers } from 'redux';
import uuid from 'uuid';

const expensesReducerDefaultState = [];
const filtersReducerDefaultState = {
    text: ' ',
    sortBy: 'date',
    startDate: undefined,
    endDate: undefined
};

const addExpense = ({ description = '', note = "", amount = "", createdAt = 0 } = {}) => ({
    type: 'ADD_EXPENSE',
    expense: {
        id: uuid(),
        description,
        note,
        amount,
        createdAt
    },
});

const removeExpense = ({ id = "" } = {}) => ({
    type: 'REMOVE_EXPENSE',
    id,
});

const editExpense = ({ id, updates } = {}) => ({
    type: 'EDIT_EXPENSE',
    id,
    updates,
});

const setTextFilter = (text = '') => ({
    type: 'SET_TEXT_FILTER',
    text
});

const sortByAmount = () => ({
    type: 'SORT_BY_AMOUNT',
});

const sortByDate = () => ({
    type: 'SORT_BY_DATE',
});

const setStartDate = (startDate) => ({
    type: 'SET_START_DATE',
    startDate
});


const setEndDate = (endDate) => ({
    type: 'SET_END_DATE',
    endDate
});


const expenseReducer = ((state = expensesReducerDefaultState, action) => {
    switch (action.type) {
        case 'ADD_EXPENSE':
            return [...state, action.expense];

        case 'REMOVE_EXPENSE':
            return state.filter((expense) => { return expense.id !== action.id });
        case 'EDIT_EXPENSE':
            return state.map((expense) => {
                if (expense.id === action.id) {
                    return {
                        ...expense,
                        ...action.updates
                    }
                }
                else {
                    return expense;
                }
            });

        default: {
            return state;
        }
    }
});

const filteReducer = ((state = filtersReducerDefaultState, action) => {
    switch (action.type) {
        case 'SET_TEXT_FILTER':
            return {
                ...state,
                text: action.text
            }
        case 'SORT_BY_AMOUNT':
            return {
                ...state,
                sortBy: 'amount'
            }
        case 'SORT_BY_DATE':
            return {
                ...state,
                sortBy: 'date'
            }

        case 'SET_START_DATE':
            return {
                ...state,
                startDate: action.startDate ? action.startDate: 0,
            }
        case 'SET_END_DATE':
            return {
                ...state,
                endDate: action.endDate ? action.endDate : 0,
            }
        default: {
            return state;
        }
    }
});



const store = createStore(
    combineReducers({
        expenses: expenseReducer,
        filters: filteReducer
    }));

store.subscribe(() => {
    console.log(store.getState());
});

// const expenseA = store.dispatch(addExpense({ description: 'RentA', amount: 1200 }));
// const expenseB = store.dispatch(addExpense({ description: 'RentA', amount: 1200 }));

// store.dispatch(editExpense({ id: expenseB.expense.id, updates: { description: "NOVA" } }));
store.dispatch(setStartDate(123));
store.dispatch(setStartDate());
store.dispatch(setEndDate(123));


const demoState = {
    expenses: [{
        id: '',
        description: "Any desc",
        note: "any note",
        amount: 46546,
        createdAt: 0
    }],
    filters: {
        text: 'asd',
        sortBy: 'amount',
        startDate: undefined,
        endDate: undefined
    }
};




