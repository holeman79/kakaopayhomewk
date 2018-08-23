import React, {Component} from 'react'
import {withStyles} from "@material-ui/core/styles/index";
import axios from 'axios'
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import Checkbox from '@material-ui/core/Checkbox';
import Divider from '@material-ui/core/Divider';
import TextField from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import Button from '@material-ui/core/Button';
import { browserHistory } from 'react-router';

const styles = theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing.unit * 3,
        overflowX: 'auto',
    },
    button: {
        margin: theme.spacing.unit,
    },
    textField: {
        marginLeft: theme.spacing.unit,
        marginRight: theme.spacing.unit,
        width: 200,
    },
    margin: {
        fontSize: 19,
        marginTop: theme.spacing.unit,
        marginLeft: theme.spacing.unit,
        marginRight: theme.spacing.unit,
    },
    marginTop: {
        marginTop: theme.spacing.unit,
    },
    formControl: {
        margin: theme.spacing.unit,
        minWidth: 120,
    },
    selectEmpty: {
        marginTop: theme.spacing.unit * 2,
    },
});
class ReserveConfRoom extends Component {
    constructor(props) {
        super(props)
        this.state = {
            date: '2018-08-23',
            reserver: 'frodo',
            timeFrom: '07:00',
            timeTo: '07:30',
            flag: '0',
            confName: '',
            repeatCount: '',
            repeatChecked: false,
        }
    }
    handleChange = name => event => {
        this.setState({
            [name]: event.target.value,
        });
    };
    handleChangeSelect = event => {
        this.setState({ [event.target.name]: event.target.value });
    };
    handleClickToBack(){
        browserHistory.push('/');
    }
    reserveClick =  () => {
        if(this.state.reserver === '')
            return alert('예약자명을 입력하세요.')
        if(this.state.date === '')
            return alert('날짜를 입력하세요.')
        if(this.state.timeFrom === '')
            return alert('시작시간을 입력하세요.')
        if(this.state.timeTo === '')
            return alert('종료시간을 입력하세요.')
        if(this.state.timeTo.replace(":", "") - this.state.timeFrom.replace(":", "") <= 0)
            return alert('시간을 잘못 입력하였습니다.')
        if(this.state.confName === '')
            return alert('회의실을 입력하세요.')
        if(this.state.repeatChecked && this.state.repeatCount === '')
            return alert('반복예약 회수를 입력하세요.')
        axios.post('/api/reserveinfo/',{
            reserver: this.state.reserver,
            date: this.state.date.replace("-", "").replace("-", ""),
            timeFrom: this.state.timeFrom,
            timeTo: this.state.timeTo,
            confId: this.state.confName,
            repeatCount: this.state.repeatChecked ? this.state.repeatCount : 1
        }).then(response => {
            if (response.status === 200) {
                if(response.data === "")
                    return alert('이미 예약된 회의실이 있습니다.')
                else
                    browserHistory.push('/');
            }
        })
    }

    render() {
        const {classes} = this.props;
        return (
            <div className={classes.root}>
                <h1 align="center">회의실 예약 페이지</h1>
                <hr/>
                <List component="nav">

                    <ListItem>
                        <TextField
                            id="reserver"
                            label="예약자명"
                            defaultValue="frodo"
                            className={classes.textField}
                            margin="normal"
                            onChange={this.handleChange('reserver')}
                            value={this.state.reserver}
                        />
                    </ListItem>
                    <ListItem>
                        <TextField
                            id="date"
                            label="예약날짜"
                            type="date"
                            className={classes.textField}
                            onChange={this.handleChange('date')}
                            InputLabelProps={{
                                shrink: true,
                            }}
                            value={this.state.date}
                        />
                        <TextField
                            id="timeFrom"
                            label="Start Time"
                            type="time"
                            defaultValue="07:30"
                            value={this.state.timeFrom}
                            className={classes.textField}
                            onChange={this.handleChange('timeFrom')}
                            InputLabelProps={{
                                shrink: true,
                            }}
                            inputProps={{
                                step: 1800, // 30 min
                            }}
                        />
                        <Typography className={classes.margin}>
                            ~
                        </Typography>
                        <TextField
                            id="timeTo"
                            label="End Time"
                            type="time"
                            defaultValue="07:30"
                            value={this.state.timeTo}
                            className={classes.textField}
                            onChange={this.handleChange('timeTo')}
                            InputLabelProps={{
                                shrink: true,
                            }}
                            inputProps={{
                                step: 1800, // 30 min
                            }}
                        />

                    </ListItem>
                    <ListItem>
                        <FormControl className={classes.formControl}>
                            <InputLabel shrink htmlFor="age-label-placeholder">
                                회의실
                            </InputLabel>
                            <Select
                                value={this.state.confName}
                                onChange={this.handleChangeSelect}
                                input={<Input name="confName" id="age-label-placeholder" />}
                                displayEmpty
                                name="confName"
                                className={classes.selectEmpty}
                            >
                                <MenuItem value={1}>
                                    <em>roomA</em>
                                </MenuItem>
                                <MenuItem value={2}>roomB</MenuItem>
                                <MenuItem value={3}>roomC</MenuItem>
                                <MenuItem value={4}>roomD</MenuItem>
                                <MenuItem value={5}>roomE</MenuItem>
                                <MenuItem value={6}>roomF</MenuItem>
                            </Select>
                        </FormControl>
                    </ListItem>
                    <ListItem>
                        <Checkbox
                            id="checkBoxRepeat"
                            checked={this.state.checked}
                            onChange={(event, checked) => this.setState({ repeatChecked: checked })}
                            tabIndex={-1}
                            disableRipple
                        />
                        <Typography variant="subheading" className={classes.marginTop}>
                            반복예약
                        </Typography>
                        <FormControl className={classes.formControl}>
                            <Select
                                id="repeatCount"
                                value={this.state.repeatCount}
                                onChange={this.handleChangeSelect}
                                input={<Input name="repeatCount" id="age-label-placeholder" />}
                                displayEmpty
                                dir='rtl'
                                name="repeatCount"
                                className={classes.selectEmpty}
                            >
                                <MenuItem value={2}>
                                    <em>2</em>
                                </MenuItem>
                                <MenuItem value={3}>3</MenuItem>
                                <MenuItem value={4}>4</MenuItem>
                                <MenuItem value={5}>5</MenuItem>
                                <MenuItem value={6}>6</MenuItem>
                                <MenuItem value={7}>7</MenuItem>
                            </Select>
                        </FormControl>
                        <Typography variant="subheading" className={classes.marginTop}>
                            회
                        </Typography>
                    </ListItem>
                    <ListItem>
                        <Button variant="contained" color="secondary" className={classes.button} onClick={this.reserveClick}>
                            예약하기
                        </Button>
                        <Button variant="contained" component="span" className={classes.button} onClick={this.handleClickToBack}>
                            뒤로가기
                        </Button>
                    </ListItem>
                </List>
                <Divider />
            </div>
        );
    }
}
export default withStyles(styles)(ReserveConfRoom);