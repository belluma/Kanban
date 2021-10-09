import React, { useEffect } from 'react';


import './App.css';

import Header from "./Components/header/Header";
import ListContainer from "./Components/list-container/ListContainer";
import {useAppDispatch} from "./app/hooks";
import {getApiData} from "./Components/list-container/TodoListSlicer";

function App() {
    const dispatch = useAppDispatch();
    useEffect( () => {
        dispatch(getApiData())
    })
  return (
    <div className="App">
<Header />
      <ListContainer />
    </div>
  );
}

export default App;
