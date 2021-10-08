import React from 'react'

//component imports

//interface imports

type Props = {};

function TodoList(props: Props){
    const dummy = [...Array(50)].map(item => <li style={{fontSize:30}}>Life ho! sail to be robed.</li>)

    return(
       <ul>{dummy}</ul>
    )
}

export default TodoList;
