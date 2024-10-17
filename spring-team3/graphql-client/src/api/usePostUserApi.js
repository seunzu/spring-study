import { gql, useMutation } from "@apollo/client";

export const CREATE_USER = gql`
  mutation createUser(
    $name: String!
    $email: String!
    $hobby: String
    $favoriteColor: String
  ) {
    createUser(
      name: $name
      email: $email
      hobby: $hobby
      favoriteColor: $favoriteColor
    ) {
      name
      email
      hobby
      favoriteColor
    }
  }
`;

export default function usePostUserApi() {
  return useMutation(CREATE_USER, {
    refetchQueries: ["getAllUsers"],
  });
}
