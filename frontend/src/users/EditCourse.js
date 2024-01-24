import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditCourse() {
    let navigate = useNavigate();

    const { id } = useParams();

    const [course, setCourse] = useState({
        name: "",
        content: "",
        author: "",
        tags: "",
    });

    const {name, content, author, tags  } = course;

    const onInputChange = (e) => {
        setCourse({ ...course, [e.target.name]: e.target.value });
    };

    useEffect(() => {
        loadCourse();
    }, []);

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.put(`http://localhost:8080/api/v1/course/update`, course);
        navigate("/");
    };

    const loadCourse = async () => {
        const result = await axios.get(`http://localhost:8080/api/v1/course/get/id/${id}`);
        setCourse(result.data);
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Edit Course</h2>

                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="Name" className="form-label">
                                Name
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your name"
                                name="name"
                                value={name}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Content" className="form-label">
                                Content
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter Content"
                                name="content"
                                value={content}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Author" className="form-label">
                                Author
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter author"
                                name="author"
                                value={author}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Tags" className="form-label">
                                Tags
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter Tags"
                                name="tags"
                                value={tags}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <button type="submit" className="btn btn-outline-primary">
                            Submit
                        </button>
                        <Link className="btn btn-outline-danger mx-2" to="/">
                            Cancel
                        </Link>
                    </form>
                </div>
            </div>
        </div>
    );
}