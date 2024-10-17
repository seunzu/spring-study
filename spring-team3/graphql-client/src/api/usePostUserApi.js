import { gql, useMutation } from "@apollo/client";

export const CREATE_USER = gql`
  mutation createUser($name: String!, $email: String!) {
    createUser(name: $name, email: $email) {
      name
      email
    }
  }
`;

export default function usePostUserApi() {
  return useMutation(CREATE_USER, {
    refetchQueries: ["getAllUsers"],
  });
}
