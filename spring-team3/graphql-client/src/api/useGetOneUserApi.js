import { gql, useQuery } from "@apollo/client";

export const GET_USER_BY_ID = gql`
  query getUserById($id: Long!) {
    getUserById(id: $id) {
      id
      name
      email
    }
  }
`;

export default function useGetUsersApi() {
  return useQuery(GET_USER_BY_ID);
}
