import React, {useEffect, useRef} from 'react';


import './App.css';

import Header from "./Components/header/Header";
import ListContainer from "./Components/list-container/ListContainer";
import {useAppDispatch} from "./app/hooks";
import {getApiData} from "./Components/list-container/TodoListSlicer";
import WarningPopper from "./Components/warning-popper/WarningPopper";

function App() {
    const dispatch = useAppDispatch();
    useEffect(() => {
        dispatch(getApiData())
    })
    const errorAnchorRef = useRef(null);
    return (
        <div ref={errorAnchorRef} className="App">
            <Header />
            <ListContainer/>
<WarningPopper anchor={errorAnchorRef.current} />
        </div>
    );
}

export default App;
