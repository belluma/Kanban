import React from 'react'
import {useAppDispatch, useAppSelector} from '../../../../app/hooks';
import {getApiData, todosByStatus} from "../../TodoListSlicer";
import {updateTodos} from "../../../../services/apiService";
import {selectCheckedDoing, selectCheckedDone, selectCheckedTodo} from "./ButtonSlicer";

//component imports
import {Button, Grid} from "@mui/material";

//interface imports

type Props = {
    index:number
};

function ButtonContainer({index}: Props) {
    const dispatch = useAppDispatch();

    const checkedLeft = useAppSelector(selectCheckedTodo);
    const checkedMiddle = useAppSelector(selectCheckedDoing);
    const checkedRight = useAppSelector(selectCheckedDone);
    const todos = useAppSelector(todosByStatus["TODO"])
    const doing = useAppSelector(todosByStatus["DOING"])
    const done = useAppSelector(todosByStatus["DONE"])

    const advanceTodos = () => {
        const ids = index ? checkedMiddle : checkedLeft;
        updateTodos(ids, true)
            .then(() => dispatch(getApiData()));
    }
    const revertTodos = () => {
        const ids = index ? checkedRight : checkedMiddle;
        updateTodos(ids, false)
            .then(() => dispatch(getApiData()));
    }

    return (
        <Grid item xs={1}>
            <Grid container direction="column" alignItems="center">
                <Button
                    sx={{my: 0.5}}
                    variant="outlined"
                    size="small"
                    onClick={advanceTodos}
                    disabled={index ? !doing.length : !todos.length}
                    aria-label="move selected right"
                >
                    &gt;
                </Button>
                <Button
                    sx={{my: 0.5}}
                    variant="outlined"
                    size="small"
                    onClick={revertTodos}
                    disabled={index ? !done.length : !doing.length}
                    aria-label="move selected left"
                >
                    &lt;
                </Button>
            </Grid>
        </Grid>
    )
}
export default ButtonContainer;
