import React, {Component} from 'react'
import axios from 'axios'
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { withStyles } from '@material-ui/core/styles';
import purple from '@material-ui/core/colors/purple';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import { browserHistory } from 'react-router';

const styles = theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing.unit * 3,
        overflowX: 'auto',
    },
    table: {
        minWidth: 700,
    },
    cell: {
        textAlign: 'center',
        padding: 5,
        height: 2
    },
    reserve: {
        backgroundColor: purple[500],
        textAlign: 'center',
        paddingRight: 24
    },
    button: {
        margin: theme.spacing.unit,
    },
    head: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
        textAlign: 'center',
        paddingRight: 24,
        fontSize: 15
    },
});


const times = [
    '07:00', '07:30', '08:00', '08:30', '09:00', '09:30', '10:00', '10:30', '11:00', '11:30', '12:00',
    '12:30', '13:30', '14:00', '14:30', '15:00', '15:30', '16:00', '16:30', '17:00', '17:30', '18:00',
    '18:30', '19:00', '19:30', '20:00', '20:30', '21:00', '21:30', '22:00', '22:30', '23:00', '23:30'
]

class ConfRoomMain extends Component {
    constructor(props) {

        super(props)
        this.state = {
            confData: [],
            reserveInfoData: [],
            date: '2018-08-23',
            flag: '0'
        }
        this.selectReserveInfoData = this.selectReserveInfoData.bind(this);
    }
    componentDidMount() {
        this.fetchConfData();
        this.fetchReserveInfoData();
    }

    fetchConfData(){
        axios.get('/api/confroom/')
            .then(response => {
                const confData = response.data;
                this.setState({
                    confData: confData
                })
            })
    }

    fetchReserveInfoData(date = this.state.date.replace("-", "").replace("-","")){
        axios.get(`/api/reserveinfo/${date}`)
            .then(response => {
                const reserveInfoData = response.data;
                this.setState({
                    reserveInfoData: reserveInfoData

                })
            })
    }
    selectReserveInfoData(){
        this.fetchReserveInfoData(this.state.date.replace("-", "").replace("-",""))
    }
    handleChange = name => event => {
        this.setState({
            [name]: event.target.value,
        });
    };

    handleClickToReserve(){
        browserHistory.push('/reserveConfRoom');
    }
    render() {
        const { classes } = this.props;
        return (
            <div className={classes.root}>
                <h1 align="center">KaKaoPay Conference Room!</h1>
                <TextField
                    id="date"
                    label="날짜"
                    type="date"
                    className={classes.textField}
                    onChange={this.handleChange('date')}
                    InputLabelProps={{
                        shrink: true,
                    }}
                    value={this.state.date}
                />
                <Button variant="outlined" className={classes.button} onClick={this.selectReserveInfoData}>
                    회의실 조회하기
                </Button>
                <Button variant="outlined" className={classes.button} onClick={this.handleClickToReserve}>
                    회의실 예약하기
                </Button>
                <hr/>
                <Table className={classes.table}>
                    <TableHead>
                        <TableRow>
                            <TableCell className={classes.head} width={90}>회의실 목록</TableCell>
                            {this.state.confData.map(row =>{
                                return (
                                    <TableCell component="th" className={classes.head}> {row.name}</TableCell>
                                )
                            })}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {times.map(row =>{
                            return (
                                <TableRow>
                                    <TableCell component="th" className={classes.cell}> {row}</TableCell>
                                    {
                                        this.state.confData.map(row2 => {
                                            return (
                                                <TableCell component="th"
                                                           className={  this.state.reserveInfoData.map(row3 => {
                                                               return ( row3.confName === row2.name && (row3.timeFrom.replace(":", "") <= row.replace(":", "") && row3.timeTo.replace(":", "") > row.replace(":", "")) ? classes.reserve : '' )})}
                                                >
                                                    {this.state.reserveInfoData.map(row3 => {
                                                        return ( row3.confName === row2.name && (row3.timeFrom.replace(":", "") <= row.replace(":", "") && row3.timeTo.replace(":", "") > row.replace(":", "")) ?
                                                            row3.repeatCount > 1 ? '(반복 ' + row3.repeatCount +'회) ' + row3.reserver : '' + row3.reserver : undefined )})}
                                                </TableCell>
                                            )
                                        })
                                    }
                                </TableRow>
                            );
                        })}
                    </TableBody>
                </Table>
            </div>
        );
    }
}

export default withStyles(styles)(ConfRoomMain);