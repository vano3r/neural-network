package org.example.network;

import lombok.Getter;
import org.example.matrix.Matrix;
import org.example.matrix.MatrixUtil;
import org.example.network.util.ActivationFunction;

/**
 *
 */
public class NeuralNetwork {
    private final int inputNodes;
    private final int hiddenNodes;
    private final int outputNodes;
    @Getter
    private final double learningRate;

    private Matrix weightHiddenInput;
    private Matrix weightHiddenOutput;

    /**
     * @param inputNodes
     * @param hiddenNodes
     * @param outputNodes
     * @param learningRate
     */

    public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes, double learningRate) {
        this.inputNodes = inputNodes;
        this.hiddenNodes = hiddenNodes;
        this.outputNodes = outputNodes;
        this.learningRate = learningRate;

        this.weightHiddenInput = MatrixUtil.random(-0.5, 0.5, hiddenNodes, inputNodes);
        this.weightHiddenOutput = MatrixUtil.random(-0.5, 0.5, outputNodes, hiddenNodes);
    }

    /**
     * Запускает нейронную сеть
     *
     * @param inputMatrix транспонированная матрица
     * @return output nodes
     */
    public Matrix query(Matrix inputMatrix) {
        return activation(MatrixUtil.mul(weightHiddenOutput,
                activation(MatrixUtil.mul(weightHiddenInput, inputMatrix))));
    }

    /**
     * Тренировка нейронной сети
     *
     * @param inputMatrix  транспонированная матрица
     * @param targetMatrix транспонированная матрица
     */
    public void train(Matrix inputMatrix, Matrix targetMatrix) {
        // Расчитать исходящие сигналы для выходного слоя
        Matrix hiddenOutputs = activation(MatrixUtil.mul(weightHiddenInput, inputMatrix));
        Matrix finalOutputs = activation(MatrixUtil.mul(weightHiddenOutput, hiddenOutputs));

        // Ошибки выходного слоя = (целевое значени - фактическое значение)
        Matrix outputErrors = MatrixUtil.sub(targetMatrix, finalOutputs);
        Matrix hiddenErrors = MatrixUtil.mul(MatrixUtil.transpose(weightHiddenOutput), outputErrors);

        // Обновить весовые коэффициенты для связей между скрытым и выходным слоями
        weightHiddenOutput.add(MatrixUtil.mul(learningRate, MatrixUtil.mul(
                MatrixUtil.scalar(MatrixUtil.scalar(outputErrors, finalOutputs), MatrixUtil.sub(1.0, finalOutputs)),
                MatrixUtil.transpose(hiddenOutputs))));

        weightHiddenInput.add(MatrixUtil.mul(learningRate, MatrixUtil.mul(
                MatrixUtil.scalar(MatrixUtil.scalar(hiddenErrors, hiddenOutputs), MatrixUtil.sub(1.0, hiddenOutputs)),
                MatrixUtil.transpose(inputMatrix))));
    }

    /**
     * Активационная функция
     *
     * @param matrix
     * @return
     */
    public Matrix activation(Matrix matrix) {
        return ActivationFunction.sigmoid(matrix);
    }
}
