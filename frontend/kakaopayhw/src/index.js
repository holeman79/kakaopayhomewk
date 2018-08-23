import React from 'react';
import ReactDOM from 'react-dom';
import ConfRoomMain from './containers/ConfRoomMain'
import ReserveConfRoom from './containers/ReserveConfRoom'
import {Router, Route, browserHistory } from 'react-router';
import axios from 'axios'


axios.defaults.baseURL = 'http://localhost:8080'

ReactDOM.render(
    <Router history={browserHistory}>
        <Route path={"/"} component={ConfRoomMain}
        />
        <Route path={"/reserveConfRoom"} component={ReserveConfRoom}/>
    </Router>, document.getElementById('root')
);

