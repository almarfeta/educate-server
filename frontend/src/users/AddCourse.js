import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function AddCourse() {
    let navigate = useNavigate();

    const [course, setCourse] = useState({
        name: '',
        author: '',
        tags: '',
        content: null, // For file upload
    });

    const { name, author, tags, content } = course;

    const onInputChange = (e) => {
        if (e.target.name === 'content') {
            setCourse({ ...course, [e.target.name]: e.target.files[0] });
        } else {
            setCourse({ ...course, [e.target.name]: e.target.value });
        }
    };

    const onSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('name', name);
        formData.append('author', author);
        formData.append('tags', tags);
        formData.append('content', content);

        try {
            await axios.post('http://localhost:8080/api/v1/course/add', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            navigate('/');
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Add Course</h2>

                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="Name" className="form-label">
                                Name
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter course name"
                                name="name"
                                value={name}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Author" className="form-label">
                                Author
                            </label>
                            <input
                                type="text"
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
                                type="text"
                                className="form-control"
                                placeholder="Enter tags"
                                name="tags"
                                value={tags}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Content" className="form-label">
                                Content (PDF File)
                            </label>
                            <input
                                type="file"
                                className="form-control"
                                name="content"
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <button type="submit" className="btn btn-outline-primary" >
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
