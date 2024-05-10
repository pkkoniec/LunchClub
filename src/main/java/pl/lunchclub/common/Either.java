package pl.lunchclub.common;

import java.util.function.Function;

public sealed interface Either<L, R> permits Either.Left, Either.Right {

    static <L, R> Either<L, R> right(R right) {
        return new Right<>(right);
    }

    static <L, R> Either<L, R> left(L left) {
        return new Left<>(left);
    }

    static <R> Either<Failure, R> failure(String reason) {
        return new Left<>(new Failure(
                reason,
                Clock.epoch()
        ));
    }

    static <R> Either<Failure, R> notImplemented() {
        return failure("NOT_IMPLEMENTED");
    }

    boolean isRight();

    <L2, R2> Either<L2, R2> map(
            Function<L, L2> leftMap,
            Function<R, R2> rightMap
    );

    R getRightOrThrow();

    default <R2> Either<L, R2> mapRight(Function<R, R2> mapRight) {
        return map(
                Function.identity(),
                mapRight
        );
    }

    record Left<L, R>(L left) implements Either<L, R> {
        @Override
        public boolean isRight() {
            return false;
        }

        @Override
        public <L2, R2> Either<L2, R2> map(
                Function<L, L2> leftMap,
                Function<R, R2> rightMap
        ) {
            return new Left<>(leftMap.apply(left));
        }

        @Override
        public R getRightOrThrow() {
            throw new IllegalStateException("EITHER_IS_LEFT");
        }
    }

    record Right<L, R>(R right) implements Either<L, R> {
        @Override
        public boolean isRight() {
            return true;
        }

        @Override
        public <L2, R2> Either<L2, R2> map(
                Function<L, L2> leftMap,
                Function<R, R2> rightMap
        ) {
            return new Right<>(rightMap.apply(right));
        }

        @Override
        public R getRightOrThrow() {
            return right;
        }
    }
}
