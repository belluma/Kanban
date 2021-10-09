import React from 'react'
import {useAppDispatch, useAppSelector} from '../../../../app/hooks';

//component imports
import {Button, Grid} from "@mui/material";
import {selectCheckedDoing, selectCheckedDone, selectCheckedTodo} from "./ButtonSlicer";
import {updateTodos} from "../../../../services/apiService";
import {getApiData} from "../../TodoListSlicer";

//interface imports

type Props = {
    index:number
    // disableAdvance: boolean,
    // disableRevert: boolean,
    // checked: number[]
};

function ButtonContainer({index}: Props) {
    const dispatch = useAppDispatch();

    const checkedLeft = useAppSelector(selectCheckedTodo);
    const checkedMiddle = useAppSelector(selectCheckedDoing);
    const checkedRight = useAppSelector(selectCheckedDone);
    const advanceTodos = () => {
        const ids = index ? checkedMiddle : checkedLeft;
        console.log(123)
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
                    // disabled={left.length === 0}
                    aria-label="move selected right"
                >
                    &gt;
                </Button>
                <Button
                    sx={{my: 0.5}}
                    variant="outlined"
                    size="small"
                    onClick={revertTodos}
                    // disabled={middleChecked.length === 0}
                    aria-label="move selected left"
                >
                    &lt;
                </Button>
            </Grid>
        </Grid>
    )
}

export default ButtonContainer;
