import { useState } from "react";
import usePostUserApi from "../api/usePostUserApi";
import useDeleteUserApi from "../api/useDeleteUserApi";
import useUpdateUserApi from "../api/useUpdateUserApi";

const RequestForm = ({ mode }) => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [hobby, setHobby] = useState("");
  const [favoriteColor, setFavoriteColor] = useState("");
  const [userId, setUserId] = useState();

  const [postUser] = usePostUserApi();
  const [deleteUser] = useDeleteUserApi();
  const [updateUser] = useUpdateUserApi();

  if (mode === "get") return <></>;
  return (
    <>
      <h2>{mode.toUpperCase()}</h2>
      {/* {mode === "get" && (
        <>
          <input type="number" placeholder="userId" />
          <button>조회</button>
        </>
      )} */}

      {mode === "post" && (
        <>
          <input
            type="text"
            placeholder="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <input
            type="text"
            placeholder="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            type="text"
            placeholder="hobby"
            value={hobby}
            onChange={(e) => setHobby(e.target.value)}
          />
          <input
            type="text"
            placeholder="favoriteColor"
            value={favoriteColor}
            onChange={(e) => setFavoriteColor(e.target.value)}
          />
          <button
            type="submit"
            onClick={() =>
              postUser({ variables: { name, email, hobby, favoriteColor } })
            }
          >
            전송
          </button>
        </>
      )}

      {mode === "delete" && (
        <>
          <input
            type="number"
            placeholder="userId"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
          />
          <button
            type="submit"
            onClick={() => deleteUser({ variables: { id: userId } })}
          >
            전송
          </button>
        </>
      )}
      {mode === "update" && (
        <>
          <input
            type="number"
            placeholder="userId"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
          />
          <input
            type="text"
            placeholder="username"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <input
            type="text"
            placeholder="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            type="text"
            placeholder="hobby"
            value={hobby}
            onChange={(e) => setHobby(e.target.value)}
          />
          <input
            type="text"
            placeholder="favoriteColor"
            value={favoriteColor}
            onChange={(e) => setFavoriteColor(e.target.value)}
          />
          <button
            type="submit"
            onClick={(e) =>
              updateUser({
                variables: { id: userId, name, email, hobby, favoriteColor },
              })
            }
          >
            전송
          </button>
        </>
      )}
    </>
  );
};

export default RequestForm;
