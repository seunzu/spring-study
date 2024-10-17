import { useState, useEffect } from "react";
import RequestForm from "./components/RequestForm";
import useGetAllUsersApi from "./api/useGetAllUsersApi";
import "./App.css";

function App() {
  const [mode, setMode] = useState("get");
  const [fields, setFields] = useState(["id"]);
  const { loading, error, data } = useGetAllUsersApi(fields);

  const onChangeMode = (e) => setMode(e.target.value);

  const onToggleColumn = ({ target }) => {
    if (target.checked) {
      setFields(fields.concat(target.value));
    } else {
      // 최소 하나는 필수
      if (fields.length === 1) {
        target.checked = true;
        return;
      }
      setFields(fields.filter((v) => v !== target.value));
    }
  };

  return (
    <main>
      <div id="form-area">
        <h2>요청</h2>
        <select onChange={onChangeMode}>
          <option value="get">-</option>
          <option value="post">POST</option>
          <option value="delete">DELETE</option>
          <option value="update">UPDATE</option>
        </select>
        <RequestForm mode={mode} />
      </div>
      <div id="form-area">
        <div>
          <h2>응답</h2>
          <fieldset>
            <legend>User Info</legend>
            <label>
              <input
                type="checkbox"
                value="id"
                defaultChecked
                onChange={onToggleColumn}
              />
              id
            </label>
            <label>
              <input type="checkbox" value="name" onChange={onToggleColumn} />
              name
            </label>
            <label>
              <input type="checkbox" value="email" onChange={onToggleColumn} />
              email
            </label>
          </fieldset>
        </div>
        <ul id="user-card-list">
          {data?.getAllUsers?.map((d) => {
            return (
              <div key={d.id} className="user-card">
                <p>id: {d.id}</p>
                <p>name: {d.name}</p>
                <p>email: {d.email}</p>
              </div>
            );
          })}
        </ul>
      </div>
    </main>
  );
}

export default App;
