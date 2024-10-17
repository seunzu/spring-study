import { gql, useMutation } from "@apollo/client";

export const UPDATE_USER = gql`
  mutation updateUser($id: ID!, $name: String!, $email: String!) {
    updateUser(
      id: $id
      name: $name
      email: $email
      hobby: $hobby
      favoriteColor: $favoriteColor
    ) {
      id
      name
      email
      hobby
      favoriteColor
    }
  }
`;

export default function useUpdateUserApi() {
  return useMutation(UPDATE_USER, { refetchQueries: ["getAllUsers"] });
}
