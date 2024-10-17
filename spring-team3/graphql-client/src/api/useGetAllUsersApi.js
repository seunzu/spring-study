import { gql, useQuery } from "@apollo/client";

export const GET_ALL_USERS = (fields) => gql`
  query getAllUsers {
    getAllUsers {
      ${fields.join(" ")}
    }
  }
`;

export default function useGetAllUsersApi(fields) {
  return useQuery(GET_ALL_USERS(fields));
}
