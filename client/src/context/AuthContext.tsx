import { Navigate } from "react-router-dom";

interface AuthContextProps {
  children: React.ReactNode;
}

export const AuthContext = ({ children }: AuthContextProps) => {
  // TODO: Implement isAuth once OAuth is ready
  const isAuth = false;

  if (!isAuth) {
    return <Navigate to="/auth" replace />;
  }

  return <>{children}</>;
};
