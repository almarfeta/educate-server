import React, { useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Container ,Paper,Button} from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),

        },
    },
}));

export default function Course() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[name,setName]=useState('')
    const[content,setContent]=useState('')
    const[author,setAuthor]=useState('')
    const[tags,setTags]=useState('')
    const[Courses,setCourses]=useState([])
    const classes = useStyles();

    const handleClick=(e)=>{
        e.preventDefault()
        const Course={name,content,author,tags}
        console.log(Course)
        fetch("http://localhost:3000/api/v1/course/add",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(Course)

        }).then(()=>{
            console.log("New Course added")
        })
    }

    useEffect(()=>{
        fetch("http://localhost:3000/api/v1/course/get/all")
            .then(res=>res.json())
            .then((result)=>{
                    setCourses(result);
                }
            )
    },[])
    return (

        <Container>
            <Paper elevation={3} style={paperStyle}>
                <h1 style={{color:"blue"}}><u>Add Course</u></h1>

                <form className={classes.root} noValidate autoComplete="off">

                    <TextField id="outlined-basic" label="Course Name" variant="outlined" fullWidth
                               value={name}
                               onChange={(e)=>setName(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="Course Content" variant="outlined" fullWidth
                               value={content}
                               onChange={(e)=>setContent(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="Course Author" variant="outlined" fullWidth
                               value={author}
                               onChange={(e)=>setAuthor(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="Course Tags" variant="outlined" fullWidth
                               value={tags}
                               onChange={(e)=>setTags(e.target.value)}
                    />
                    <Button variant="contained" color="secondary" onClick={handleClick}>
                        Submit
                    </Button>
                </form>

            </Paper>
            <h1>Courses</h1>

            <Paper elevation={3} style={paperStyle}>

                {Courses.map(Course=>(
                    <Paper elevation={6} style={{margin: "10px", padding: "15px", textAlign: "left"}} key={Course.id}>
                        Id:{Course.id}<br/>
                        Name:{Course.name}<br/>
                        Content:{Course.content}<br/>
                        Author:{Course.author}<br/>
                        Tags:{Course.tags}<br/>
                    </Paper>
                ))
                }


            </Paper>



        </Container>
    );
}