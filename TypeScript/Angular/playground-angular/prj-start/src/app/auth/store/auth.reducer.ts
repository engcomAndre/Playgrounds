import { AuthEffects } from './auth.effects';
import * as AuthActions from './auth.actions';
import { User } from '../user.model';


export interface State {
  user: User;
  authError: string;
  loading: boolean;
}
const initialState = {
  user: null,
  authError: null,
  loading: false
};

export function authReducer(
  state = initialState,
  action: AuthActions.AuthActions) {

  switch (action.type) {
    case AuthActions.AUTHENTICATE_SUCCESS: {

      const user = new User(
        action.payload.email,
        action.payload.userId,
        action.payload.token,
        action.payload.expirationData);
      return {
        ...state,
        authError: null,
        user: user,
        loading: false
      };
    }
    case AuthActions.LOGOUT: {
      return {
        ...state,
        user: null,
        authError: null,
        loading: false
      };
    }
    case AuthActions.AUTO_LOGIN: {
      return {
        ...state,
        authError: null,
        loading: true
      };
    }
    case AuthActions.LOGIN_START:
    case AuthActions.SIGNUP_START: {
      return {
        ...state,
        authError: null,
        loading: true
      };
    }
    case AuthActions.CLEAR_ERROR: {
      return {
        ...state,
        authError: null,
        loading: false

      };
    }
    case AuthActions.AUTHENTICATE_FAIL: {
      return {
        ...state,
        user: null,
        authError: action.payload,
        loading: false
      };
    }
    default: {
      return {
        ...state,
        loading: false
      };
    }
  }

}

