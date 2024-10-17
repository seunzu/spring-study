import { gql, useMutation } from "@apollo/client";

export const DELETE_USER = gql`
  mutation deleteUser($id: ID!) {
    deleteUser(id: $id)
  }
`;

export default function useGetUsersApi() {
  return useMutation(DELETE_USER, { refetchQueries: ["getAllUsers"] });
}
