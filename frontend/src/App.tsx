import React, {useEffect, useRef} from 'react';
import {useAppDispatch} from "./app/hooks";
import {getApiData} from "./Components/list-container/TodoListSlicer";
import './App.css';

//components
import Header from "./Components/header/Header";
import ListContainer from "./Components/list-container/ListContainer";
import WarningPopper from "./Components/warning-popper/WarningPopper";
import NewTodoPopper from "./Components/new-todo-popper/NewTodoPopper";
import {Grid} from "@mui/material";

function App() {
    const dispatch = useAppDispatch();
    useEffect(() => {
        dispatch(getApiData())
    })
    const popupAnchor = useRef(null);
    return (
        <div className="App">
            <Header/>
            <ListContainer/>
            <Grid container justifyContent="center" alignItems="center">
                <Grid ref={popupAnchor} item>
                    <WarningPopper anchorRef={popupAnchor.current}/>
                    <NewTodoPopper anchorRef={popupAnchor.current} />
                </Grid>
            </Grid>
        </div>
    );
}

export default App;
